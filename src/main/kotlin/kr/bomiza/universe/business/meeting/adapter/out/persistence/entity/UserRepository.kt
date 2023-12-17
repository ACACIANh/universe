package kr.bomiza.universe.business.meeting.adapter.out.persistence.entity

import kr.bomiza.universe.common.enums.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository(value = "UserRepositoryMeeting")
interface UserRepository : JpaRepository<UserJpaEntity, UUID> {

    fun findByName(name: String?): Optional<UserJpaEntity>
    fun findByRole(role: UserRole): Optional<UserJpaEntity>
}