package kr.bomiza.universe.meeting.adapter.out.persistence

import kr.bomiza.universe.meeting.adapter.out.persistence.entity.AttendanceJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.UserJpaEntity
import kr.bomiza.universe.meeting.domain.model.Attendance
import kr.bomiza.universe.meeting.domain.model.User
import org.springframework.stereotype.Component

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

    fun mapToDomain(entity: UserJpaEntity): User {
        return User(entity.id, entity.name, entity.state, entity.role)
    }

    fun mapToEntity(attendance: Attendance): AttendanceJpaEntity {
        return AttendanceJpaEntity(attendance.id, mapToEntity(attendance.user), attendance.checkIn, attendance.checkOut)
    }

    fun mapToEntity(user: User): UserJpaEntity {
        return UserJpaEntity(user.id, user.name, user.state, user.role)
    }
}