package kr.bomiza.universe.business.attendance.adapter.out.persistence

import kr.bomiza.universe.business.attendance.adapter.out.persistence.entity.AttendanceJpaEntity
import kr.bomiza.universe.business.attendance.adapter.out.persistence.entity.UserJpaEntity
import kr.bomiza.universe.domain.attendance.model.Attendance
import kr.bomiza.universe.domain.attendance.model.User
import org.springframework.stereotype.Component

@Component
class AttendancePersistenceMapper {

    fun mapToDomain(attendanceJpaEntity: AttendanceJpaEntity): Attendance {
        return Attendance(attendanceJpaEntity.id, mapToDomain(attendanceJpaEntity.user), attendanceJpaEntity.checkIn, attendanceJpaEntity.checkOut)
    }

    fun mapToEntity(attendance: Attendance): AttendanceJpaEntity {
        return AttendanceJpaEntity(attendance.id, mapToEntity(attendance.user), attendance.checkIn, attendance.checkOut)
    }

    fun mapToDomain(entity: UserJpaEntity): User {
        return User(entity.id, entity.name, entity.state)
    }

    fun mapToEntity(user: User): UserJpaEntity {
        return UserJpaEntity(user.id, user.name, user.state)
    }
}