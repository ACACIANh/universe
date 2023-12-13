package kr.bomiza.universe.business.attendance.adapter.out.persistence

import kr.bomiza.universe.business.attendance.adapter.out.persistence.entity.UserRepository
import kr.bomiza.universe.business.attendance.application.port.out.LoadUserPort
import kr.bomiza.universe.common.annotation.PersistenceAdapter
import kr.bomiza.universe.domain.attendance.model.User
import kr.bomiza.universe.common.exception.NotFoundUserException
import java.util.*

@PersistenceAdapter(value = "UserPersistenceAdapterAttendance")
class UserPersistenceAdapter(
    val userRepository: UserRepository,
    val attendancePersistenceMapper: AttendancePersistenceMapper
) : LoadUserPort {

    override fun loadUser(userId: UUID): User {
        val userEntity = userRepository.findById(userId)
            .orElseThrow { NotFoundUserException(userId.toString()) }
        return attendancePersistenceMapper.mapToDomain(userEntity)
    }
}