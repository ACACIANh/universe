package kr.bomiza.universe.business.user.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository(value = "UserRepositoryUser")
interface UserRepository : JpaRepository<UserJpaEntity, UUID> {
}