package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import kr.bomiza.universe.meeting.domain.enums.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserJpaEntity, UUID> {

    fun findByName(name: String?): Optional<UserJpaEntity>
    fun findByRole(role: UserRole): Optional<UserJpaEntity>
}