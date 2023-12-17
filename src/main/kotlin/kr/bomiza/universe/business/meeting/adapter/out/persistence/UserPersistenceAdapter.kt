package kr.bomiza.universe.business.meeting.adapter.out.persistence

import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.UserRepository
import kr.bomiza.universe.business.meeting.application.port.out.LoadUserPort
import kr.bomiza.universe.common.annotation.PersistenceAdapter
import kr.bomiza.universe.common.exception.NotFoundUserException
import kr.bomiza.universe.common.enums.UserRole
import kr.bomiza.universe.domain.meeting.exception.NotFoundAdminUserException
import kr.bomiza.universe.domain.meeting.model.User
import java.util.*

@PersistenceAdapter(value = "UserPersistenceAdapterMeeting")
class UserPersistenceAdapter(
    val userRepository: UserRepository,
    val meetingPersistenceMapper: MeetingPersistenceMapper
) : LoadUserPort {

    //todo: 검증로직이 여기있는게 맞는지 검토 // attendance 동일
    override fun loadUser(userId: UUID): User {
        val userEntity = userRepository.findById(userId)
            .orElseThrow { NotFoundUserException(userId.toString()) }
        return meetingPersistenceMapper.mapToDomain(userEntity)
    }

    override fun loadAdminUser(): User {
        val userEntity = userRepository.findByRole(UserRole.ADMIN)
            .orElseThrow { NotFoundAdminUserException() }
        return meetingPersistenceMapper.mapToDomain(userEntity)
    }
}