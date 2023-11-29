package kr.bomiza.universe.domain.user

import kr.bomiza.universe.common.model.enums.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun findByName(name: String?): Optional<User>

    fun findByRole(role: UserRole): Optional<User>

}