package kr.bomiza.universe.meeting.adapter.out.persistence

import kr.bomiza.universe.meeting.adapter.out.persistence.entity.AttendanceJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.MeetingJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.MeetingUserJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.UserJpaEntity
import kr.bomiza.universe.meeting.domain.model.*
import org.springframework.stereotype.Component

// todo: 개선방법 생각해보기
@Component
class MeetingPersistenceMapper {
    //todo: 변환 확인하고 지울것
    fun mapToDomain(attendanceJpaEntity: AttendanceJpaEntity): Attendance {
        return Attendance(
            attendanceJpaEntity.id,
            mapToDomain(attendanceJpaEntity.user),
            attendanceJpaEntity.checkIn,
            attendanceJpaEntity.checkOut
        )
    }

    fun mapToEntity(attendance: Attendance): AttendanceJpaEntity {
        return AttendanceJpaEntity(attendance.id, mapToEntity(attendance.user), attendance.checkIn, attendance.checkOut)
    }

    fun mapToDomain(entity: UserJpaEntity): User {
        return User(entity.id, entity.name, entity.state, entity.role)
    }

    fun mapToEntity(user: User): UserJpaEntity {
        return UserJpaEntity(user.id, user.name, user.state, user.role)
    }

    fun mapToDomain(entity: MeetingJpaEntity): Meeting {
        return Meeting( entity.id, mapToDomain(entity.masterUser), entity.date, entity.capacityMember, MeetingUsers(entity.meetingUsers.map { mapToDomain(it) }))
    }

    fun mapToEntity(meeting: Meeting): MeetingJpaEntity {
        return MeetingJpaEntity(meeting.id, mapToEntity(meeting.masterUser),meeting.date, meeting.capacityMember, meeting.meetingUsers.meetingUsers.map { mapToEntity(it) })
    }

    fun mapToDomain(entity: MeetingUserJpaEntity): MeetingUser {
        return MeetingUser( mapToDomain(entity.meeting), mapToDomain(entity.user), entity.state, entity.joinTime, entity.guest)
    }

    fun mapToEntity(meetingUser: MeetingUser): MeetingUserJpaEntity {
        return MeetingUserJpaEntity(meetingUser.id, mapToEntity(meetingUser.meeting), mapToEntity(meetingUser.user), meetingUser.state, meetingUser.joinTime, meetingUser.guest)
    }
}