package kr.bomiza.universe.business.meeting.adapter.out.persistence

import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.MeetingRepository
import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.MeetingUserRepository
import kr.bomiza.universe.business.meeting.application.port.out.LoadMeetingPort
import kr.bomiza.universe.business.meeting.application.port.out.LoadMeetingUserPort
import kr.bomiza.universe.business.meeting.application.port.out.SaveMeetingPort
import kr.bomiza.universe.business.meeting.application.port.out.SaveMeetingUserPort
import kr.bomiza.universe.common.annotation.PersistenceAdapter
import kr.bomiza.universe.domain.meeting.exception.NotFoundMeetingException
import kr.bomiza.universe.domain.meeting.exception.NotFoundMeetingUserException
import kr.bomiza.universe.domain.meeting.model.Meeting
import kr.bomiza.universe.domain.meeting.model.MeetingUser
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@PersistenceAdapter
class MeetingPersistenceAdapter(
    val meetingRepository: MeetingRepository,
    val meetingUserRepository: MeetingUserRepository,
    val meetingPersistenceMapper: MeetingPersistenceMapper
) :
    LoadMeetingPort,
    LoadMeetingUserPort,
    SaveMeetingPort,
    SaveMeetingUserPort {

    override fun findAll(page: Pageable): List<Meeting> {
        return meetingRepository.findAllByOrderByCreatedDateDesc(page).toList()
            .map { meetingPersistenceMapper.mapToDomain(it) }
    }

    override fun loadMeeting(meetingId: UUID): Meeting {
        val entity = meetingRepository.findById(meetingId)
            .orElseThrow { NotFoundMeetingException(meetingId.toString()) }
        return meetingPersistenceMapper.mapToDomain(entity)
    }

    override fun loadMeeting(localDate: LocalDate): Meeting? {
        return meetingRepository.findByDate(localDate)?.let {
            meetingPersistenceMapper.mapToDomain(it)
        }
    }

    override fun loadMeetingUser(meetingUserId: UUID): MeetingUser {
        val entity = meetingUserRepository.findById(meetingUserId)
            .orElseThrow { NotFoundMeetingUserException(meetingUserId.toString()) }
        return meetingPersistenceMapper.mapToDomain(entity)
    }

    override fun loadMeetingUserByUserIdAndDate(userId: UUID, localDate: LocalDate): List<MeetingUser> {
        return meetingUserRepository.findByUserIdAndDate(userId, localDate)
            .map { meetingPersistenceMapper.mapToDomain(it) }
    }

    override fun saveMeeting(meeting: Meeting) {
        val entity = meetingPersistenceMapper.mapToEntity(meeting)
        meetingRepository.save(entity)
    }

    override fun saveMeetingUser(meetingUser: MeetingUser) {
        val entity = meetingPersistenceMapper.mapToEntity(meetingUser)
        meetingUserRepository.save(entity)
    }

    override fun saveMeetingUsers(meetingUsers: List<MeetingUser>) {
        val entities = meetingUsers.map {
            meetingPersistenceMapper.mapToEntity(it)
        }
        meetingUserRepository.saveAll(entities)
    }
}