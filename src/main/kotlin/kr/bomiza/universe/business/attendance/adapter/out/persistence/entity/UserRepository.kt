package kr.bomiza.universe.business.attendance.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserJpaEntity, UUID> {

    fun findByName(name: String?): Optional<UserJpaEntity>
}