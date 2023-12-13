package kr.bomiza.universe.business.attendance.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository(value = "UserRepositoryAttendance")
interface UserRepository : JpaRepository<UserJpaEntity, UUID> {

    fun findByName(name: String?): Optional<UserJpaEntity>
}