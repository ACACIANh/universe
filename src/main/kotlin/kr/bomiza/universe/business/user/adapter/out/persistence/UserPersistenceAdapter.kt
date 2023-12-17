package kr.bomiza.universe.business.user.adapter.out.persistence

import kr.bomiza.universe.business.user.adapter.out.persistence.entity.UserRepository
import kr.bomiza.universe.business.user.application.port.out.LoadUserPort
import kr.bomiza.universe.business.user.application.port.out.SaveUserPort
import kr.bomiza.universe.common.annotation.PersistenceAdapter
import kr.bomiza.universe.common.exception.NotFoundUserException
import kr.bomiza.universe.domain.user.model.User
import org.springframework.data.domain.Pageable
import java.util.*

@PersistenceAdapter(value = "UserPersistenceAdapterUser")
class UserPersistenceAdapter(
    val userRepository: UserRepository,
    val userPersistenceMapper: UserPersistenceMapper
) : LoadUserPort,
    SaveUserPort {

    override fun loadUser(userId: UUID): User {
        val userEntity = userRepository.findById(userId)
            .orElseThrow { NotFoundUserException(userId.toString()) }
        return userPersistenceMapper.mapToDomain(userEntity)
    }

    override fun loadUsers(page: Pageable): List<User> {
        return userRepository.findAll(page).toMutableList()
            .map { userPersistenceMapper.mapToDomain(it) }
    }

    override fun saveUser(user: User) {
        userRepository.save(userPersistenceMapper.mapToEntity(user))
    }
}