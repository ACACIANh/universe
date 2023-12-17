package kr.bomiza.universe.business.user.application.service

import kr.bomiza.universe.business.user.application.port.`in`.FindUserUseCase
import kr.bomiza.universe.business.user.application.port.`in`.InactiveUserUseCase
import kr.bomiza.universe.business.user.application.port.out.LoadUserPort
import kr.bomiza.universe.business.user.application.port.out.SaveUserPort
import kr.bomiza.universe.common.annotation.UseCase
import kr.bomiza.universe.domain.user.model.User
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.util.*

@UseCase
@Transactional(readOnly = true)
class UserService(
    val loadUserPort: LoadUserPort,
    val saveUserPort: SaveUserPort
) : FindUserUseCase,
    InactiveUserUseCase {

    override fun findAllUser(page: Pageable): List<User> {
        return loadUserPort.loadUsers(page)
    }

    @Transactional
    override fun inactiveUser(userId: UUID) {
        val user = loadUserPort.loadUser(userId)
        user.inactive()
        saveUserPort.saveUser(user)
    }
}