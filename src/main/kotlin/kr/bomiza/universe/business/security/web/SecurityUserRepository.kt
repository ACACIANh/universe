package kr.bomiza.universe.business.security.web

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SecurityUserRepository : JpaRepository<SecurityUserJpaEntity, UUID> {

    fun findByEmail(email: String): Optional<SecurityUserJpaEntity>
}