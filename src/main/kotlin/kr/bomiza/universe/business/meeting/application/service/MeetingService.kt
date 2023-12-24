package kr.bomiza.universe.business.meeting.application.service

import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinUpdateRequestDto
import kr.bomiza.universe.business.meeting.application.port.`in`.CreateMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.`in`.FindMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.`in`.FinishMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.`in`.JoinMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.out.*
import kr.bomiza.universe.common.annotation.UseCase
import kr.bomiza.universe.configuration.UniverseProperties
import kr.bomiza.universe.domain.meeting.exception.ExistMeetingException
import kr.bomiza.universe.domain.meeting.model.Meeting
import kr.bomiza.universe.domain.meeting.model.MeetingUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

//todo: exception 발생 위치를, Service 에서 할지, PersistenceAdapter 에서 할지 고민, 일단 persistence
@UseCase
@Transactional(readOnly = true)
class MeetingService(
    val loadUserPort: LoadUserPort,
    val loadMeetingPort: LoadMeetingPort,
    val loadMeetingUserPort: LoadMeetingUserPort,
    val saveMeetingPort: SaveMeetingPort,
    val saveMeetingUserPort: SaveMeetingUserPort,
    val universeProperties: UniverseProperties,
) : CreateMeetingUseCase,
    JoinMeetingUseCase,
    FindMeetingUseCase,
    FinishMeetingUseCase {

    @Transactional
    override fun createMeeting(userId: UUID, requestDto: MeetingCreateRequestDto): Meeting {
        loadMeetingPort.loadMeeting(requestDto.date)?.let {
            throw ExistMeetingException(it.idString())
        }
        val user = loadUserPort.loadUser(userId)
        val meeting = Meeting(user, requestDto.date, requestDto.capacityMember)
        saveMeetingPort.saveMeeting(meeting)
        return meeting
    }

    override fun findAll(page: Pageable): Collection<Meeting> {
        return loadMeetingPort.findAll(page)
    }

    @Transactional
    override fun joinMeeting(userId: UUID, meetingId: UUID, requestDto: MeetingJoinRequestDto): MeetingUser {
        val meeting = loadMeetingPort.loadMeeting(meetingId)
        val user = loadUserPort.loadUser(userId)
        val meetingUser = meeting.joinMeeting(user, requestDto.joinTime, requestDto.isGuest)
        saveMeetingUserPort.saveMeetingUser(meetingUser)
        return meetingUser
    }

    @Transactional
    override fun joinMeetingUpdate(
        userId: UUID, meetingUserId: UUID, requestDto: MeetingJoinUpdateRequestDto
    ): MeetingUser {
        val meetingUser = loadMeetingUserPort.loadMeetingUser(meetingUserId)
        val user = loadUserPort.loadUser(userId)
        meetingUser.updateUserDate(user, requestDto.joinTime, requestDto.isGuest)
        saveMeetingUserPort.saveMeetingUser(meetingUser)
        return meetingUser
    }

    @Transactional
    fun createWeekendMeetingOnMonday() {

        var intervalDays: Int = (DayOfWeek.FRIDAY.value - DayOfWeek.MONDAY.value)

        val friday = LocalDate.now().plusDays(intervalDays++.toLong())
        val saturday = LocalDate.now().plusDays(intervalDays++.toLong())
        val sunday = LocalDate.now().plusDays(intervalDays.toLong())

        this.createMeetingOnAdmin(friday)
        this.createMeetingOnAdmin(saturday)
        this.createMeetingOnAdmin(sunday)
    }

    private fun createMeetingOnAdmin(createDate: LocalDate) {
        // todo: 개선 포인트 -> usecase 랑 통합시 user 의 두번 조회 이슈, 비통합시 중복코드
        loadMeetingPort.loadMeeting(createDate)?.let {
            throw ExistMeetingException(it.idString())
        }
        val adminUser = loadUserPort.loadAdminUser();
        val meeting = Meeting(adminUser, createDate, universeProperties.meeting.capacity)
        saveMeetingPort.saveMeeting(meeting)
        log.info("createMeetingOnAdmin success createdDate: $createDate")
    }

    @Transactional
    override fun finish(userId: UUID, date: LocalDate) {
        val loadMeetingUsers = loadMeetingUserPort.loadMeetingUserByUserIdAndDate(userId, date)
            .onEach { it.finished() }
        saveMeetingUserPort.saveMeetingUsers(loadMeetingUsers)
        val meeting = loadMeetingPort.loadMeeting(date)?.also {
            it.refreshUsers()
        } ?: return
        saveMeetingPort.saveMeeting(meeting)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(MeetingService::class.java)
    }
}