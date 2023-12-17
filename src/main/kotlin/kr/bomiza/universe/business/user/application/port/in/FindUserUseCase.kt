package kr.bomiza.universe.business.user.application.port.`in`

import kr.bomiza.universe.domain.user.model.User
import org.springframework.data.domain.Pageable

interface FindUserUseCase {

    fun findAllUser(page: Pageable): List<User>
}