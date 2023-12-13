package kr.bomiza.universe.meeting.adapter.out.persistence

import kr.bomiza.universe.domain.meeting.model.*
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.AttendanceJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.MeetingJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.MeetingUserJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.UserJpaEntity
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

    fun mapToDomain(attendanceJpaEntity: AttendanceJpaEntity): Attendance {
        return Attendance(attendanceJpaEntity.id, mapToDomain(attendanceJpaEntity.user), attendanceJpaEntity.checkIn, attendanceJpaEntity.checkOut)
    }

    fun mapToEntity(attendance: Attendance): AttendanceJpaEntity {
        return AttendanceJpaEntity(attendance.id, mapToEntity(attendance.user), attendance.checkIn, attendance.checkOut)
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