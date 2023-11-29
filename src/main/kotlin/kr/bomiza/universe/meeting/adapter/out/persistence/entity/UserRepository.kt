package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import kr.bomiza.universe.meeting.domain.enums.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun findByName(name: String?): Optional<User>

    fun findByRole(role: UserRole): Optional<User>

}