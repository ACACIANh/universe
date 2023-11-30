package kr.bomiza.universe.meeting.application.legacy

import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingJoinUpdateRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.MeetingResponseDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.MeetingUsersResponseDto
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.MeetingJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.MeetingRepository
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.MeetingUserRepository
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.UserRepository
import kr.bomiza.universe.meeting.domain.enums.UserRole
import kr.bomiza.universe.meeting.domain.exception.InvalidAccessResourceException
import kr.bomiza.universe.meeting.domain.exception.NotFoundAdminUserException
import kr.bomiza.universe.meeting.domain.exception.NotFoundMeetingUserException
import kr.bomiza.universe.security.domain.SecurityUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

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
        user: SecurityUser,
        requestDto: MeetingCreateRequestDto,
    ): MeetingResponseDto {
        val userEntity = userRepository.findByEmail(user.email).orElse(null)
        val meeting = meetingRepository.save(requestDto.toEntity(userEntity))
        return MeetingResponseDto(meeting)
    }

    @Transactional
    fun joinMeeting(
        user: SecurityUser,
        meetingId: UUID,
        requestDto: MeetingJoinRequestDto,
    ): MeetingUsersResponseDto {
//        val meeting = meetingRepository.findById(meetingId)
//            .orElseThrow {
//                NotFoundMeetingException(meetingId.toString())
//            }
//
//        val meetingUserState =
//            if (meeting.checkJoinAbility(user, requestDto)) MeetingUserState.PARTICIPATION else MeetingUserState.WAITING
//
//        val meetingUser = MeetingUserJpaEntity(meeting, user, meetingUserState, requestDto.joinTime, requestDto.isGuest)
//        meeting.addMeetingUser(meetingUser)

//        return MeetingUsersResponseDto(meetingUser)
        // todo: 주석 정상화
        return joinMeetingUpdate(user, meetingId, MeetingJoinUpdateRequestDto(true, LocalTime.now()))
    }

    @Transactional
    fun joinMeetingUpdate(
        user: SecurityUser,
        meetingUserId: UUID,
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
        meetingRepository.save(MeetingJpaEntity(adminUser, createDate, CAPACITY_MEMBER))
        log.info("createMeetingOnAdmin success createdDate: $createDate")
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(MeetingService::class.java)
    }
}