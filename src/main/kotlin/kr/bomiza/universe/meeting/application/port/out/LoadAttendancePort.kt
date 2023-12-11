package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.Attendance
import org.springframework.data.domain.Pageable
import java.util.*

interface LoadAttendancePort {

    fun findByUserIdAndCheckIn(userId: UUID): Attendance?
    fun findFirstByUserIdOrderByCreatedDateDesc(userId: UUID): Attendance?
    fun findALLByUserIdOrderByCreatedDateDesc(userId: UUID, page: Pageable): List<Attendance>
}