package kr.bomiza.universe.business.user.application.port.out

import kr.bomiza.universe.domain.user.model.User
import org.springframework.data.domain.Pageable
import java.util.UUID

interface LoadUserPort {

    fun loadUser(userId: UUID): User
    fun loadUsers(page: Pageable): List<User>
}