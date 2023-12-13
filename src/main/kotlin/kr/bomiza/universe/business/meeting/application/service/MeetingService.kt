package kr.bomiza.universe.business.meeting.application.service

import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinUpdateRequestDto
import kr.bomiza.universe.business.meeting.application.port.`in`.CreateMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.`in`.FindMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.`in`.JoinMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.out.*
import kr.bomiza.universe.common.annotation.UseCase
import kr.bomiza.universe.domain.meeting.model.Meeting
import kr.bomiza.universe.domain.meeting.model.MeetingUser
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.util.*

//todo: exception 발생 위치를, Service 에서 할지, PersistenceAdapter 에서 할지 고민, 일단 peersistence
@UseCase
@Transactional(readOnly = true)
class MeetingService(
    val loadUserPort: LoadUserPort,
    val loadMeetingPort: LoadMeetingPort,
    val loadMeetingUserPort: LoadMeetingUserPort,
    val saveMeetingPort: SaveMeetingPort,
    val saveMeetingUserPort: SaveMeetingUserPort,
) : CreateMeetingUseCase,
    JoinMeetingUseCase,
    FindMeetingUseCase {

    @Transactional
    override fun createMeeting(userId: UUID, requestDto: MeetingCreateRequestDto): Meeting {
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
}