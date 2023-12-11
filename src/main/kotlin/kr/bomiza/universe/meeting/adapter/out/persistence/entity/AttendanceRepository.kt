package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface AttendanceRepository : JpaRepository<AttendanceJpaEntity, UUID> {

    @Query("SELECT a FROM AttendanceJpaEntity a WHERE a.user.id = :userId AND a.checkOut IS NULL")
    fun findByUserIdAndCheckIn(userId: UUID): AttendanceJpaEntity?
    fun findFirstByUserIdOrderByCreatedDateDesc(userId: UUID): AttendanceJpaEntity?
    fun findAllByUserIdOrderByCreatedDateDesc(userId: UUID, page: Pageable): List<AttendanceJpaEntity>
}