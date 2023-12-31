package kr.bomiza.universe.business.attendance.application.port.out

import kr.bomiza.universe.domain.attendance.model.Attendance
import org.springframework.data.domain.Pageable
import java.util.*

interface LoadAttendancePort {

    fun findByUserIdAndCheckIn(userId: UUID): Attendance?
    fun findFirstByUserIdOrderByCreatedDateDesc(userId: UUID): Attendance?
    fun findAllByUserIdOrderByCreatedDateDesc(userId: UUID, page: Pageable): List<Attendance>
}