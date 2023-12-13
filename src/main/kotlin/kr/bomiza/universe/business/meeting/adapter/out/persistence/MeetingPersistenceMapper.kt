package kr.bomiza.universe.business.meeting.adapter.out.persistence

import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.MeetingJpaEntity
import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.MeetingUserJpaEntity
import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.UserJpaEntity
import kr.bomiza.universe.domain.meeting.model.Meeting
import kr.bomiza.universe.domain.meeting.model.MeetingUser
import kr.bomiza.universe.domain.meeting.model.MeetingUsers
import kr.bomiza.universe.domain.meeting.model.User
import org.springframework.stereotype.Component

// todo: 개선방법 생각해보기
@Component
class MeetingPersistenceMapper {

    fun mapToDomain(entity: UserJpaEntity): User {
        return User(entity.id, entity.name, entity.state, entity.role)
    }

    fun mapToEntity(user: User): UserJpaEntity {
        return UserJpaEntity(user.id, user.name, user.state, user.role)
    }

    fun mapToDomain(entity: MeetingJpaEntity): Meeting {
        return Meeting( entity.id, mapToDomain(entity.masterUser), entity.date, MeetingUsers(entity.capacityMember, entity.meetingUsers.map { mapToDomain(it) }.toMutableList()))
    }

    fun mapToEntity(meeting: Meeting): MeetingJpaEntity {
        return MeetingJpaEntity(meeting.id, mapToEntity(meeting.masterUser),meeting.date, meeting.meetingUsers.capacity, meeting.meetingUsers.meetingUsers.map { mapToEntity(it) })
    }

    fun mapToDomain(entity: MeetingUserJpaEntity): MeetingUser {
        return MeetingUser( entity.id, mapToDomain(entity.meeting), mapToDomain(entity.user), entity.state, entity.joinTime, entity.guest, entity.createdDate)
    }

    fun mapToEntity(meetingUser: MeetingUser): MeetingUserJpaEntity {
        return MeetingUserJpaEntity(meetingUser.id, mapToEntity(meetingUser.meeting), mapToEntity(meetingUser.user), meetingUser.state, meetingUser.joinTime, meetingUser.guest)
    }
}