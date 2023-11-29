package kr.bomiza.universe.service

import kr.bomiza.universe.common.model.enums.MeetingUserState
import kr.bomiza.universe.common.model.enums.UserRole
import kr.bomiza.universe.common.model.dto.meeting.*
import kr.bomiza.universe.common.model.exception.*
import kr.bomiza.universe.domain.meeting.Meeting
import kr.bomiza.universe.domain.meeting.MeetingRepository
import kr.bomiza.universe.domain.meeting.MeetingUser
import kr.bomiza.universe.domain.meeting.MeetingUserRepository
import kr.bomiza.universe.domain.user.User
import kr.bomiza.universe.domain.user.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.DayOfWeek
import java.time.LocalDate

const val CAPACITY_MEMBER = 16

@Service
@Transactional(readOnly = true)
class MeetingService(
    val meetingRepository: MeetingRepository,
    val meetingUserRepository: MeetingUserRepository,
    val userRepository: UserRepository,
) {

    @Transactional
    fun createMeeting(
        user: User,
        requestDto: MeetingCreateRequestDto,
    ): MeetingResponseDto {
        val meeting = meetingRepository.save(requestDto.toEntity(user))
        return MeetingResponseDto(meeting)
    }

    @Transactional
    fun joinMeeting(
        user: User,
        meetingId: Long,
        requestDto: MeetingJoinRequestDto,
    ): MeetingUsersResponseDto {
        val meeting = meetingRepository.findById(meetingId)
            .orElseThrow {
                NotFoundMeetingException(meetingId.toString())
            }

        val meetingUserState =
            if (meeting.checkJoinAbility(user, requestDto)) MeetingUserState.PARTICIPATION else MeetingUserState.WAITING

        val meetingUser = MeetingUser(meeting, user, meetingUserState, requestDto.joinTime, requestDto.isGuest)
        meeting.addMeetingUser(meetingUser)

        return MeetingUsersResponseDto(meetingUser)
    }

    @Transactional
    fun joinMeetingUpdate(
        user: User,
        meetingUserId: Long,
        requestDto: MeetingJoinUpdateRequestDto,
    ): MeetingUsersResponseDto {

        val meetingUser = meetingUserRepository.findById(meetingUserId)
            .orElseThrow {
                NotFoundMeetingUserException(meetingUserId.toString())
            }

        if (!meetingUser.user.id!!.equals(user.id)) {
            throw InvalidAccessResourceException(meetingUser.id!!, user.id!!)
        }

        meetingUser.guest = requestDto.isGuest
        meetingUser.joinTime = requestDto.joinTime

        return MeetingUsersResponseDto(meetingUser)
    }

    fun findAll(): Collection<MeetingResponseDto> {
        val findAll = meetingRepository.findAll()
        return findAll.map(::MeetingResponseDto)
    }

    fun createWeekendMeetingOnMonday() {

        var intervalDays: Int = (DayOfWeek.FRIDAY.value - DayOfWeek.MONDAY.value)

        val friday = LocalDate.now().plusDays(intervalDays++.toLong())
        val saturday = LocalDate.now().plusDays(intervalDays++.toLong())
        val sunday = LocalDate.now().plusDays(intervalDays.toLong())

        this.createMeetingOnAdmin(friday)
        this.createMeetingOnAdmin(saturday)
        this.createMeetingOnAdmin(sunday)
    }

    fun createMeetingOnAdmin(createDate: LocalDate) {

        val adminUser = userRepository.findByRole(UserRole.ADMIN)
            .orElseThrow { NotFoundAdminUserException() }
        meetingRepository.save(Meeting(adminUser, createDate, CAPACITY_MEMBER))
        log.info("createMeetingOnAdmin success createdDate: $createDate")
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(MeetingService::class.java)
    }
}