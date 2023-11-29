package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AttendanceRepository : JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.user.id = :userId AND a.checkOut IS NULL")
    fun findByUserIdAndCheckIn(userId: Long): Attendance?
    fun findFirstByUserIdOrderByCreatedDateDesc(userId: Long): Attendance?
    fun findALLByUserIdOrderByCreatedDateDesc(userId: Long): List<Attendance>
}