package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.Attendance
import java.util.*

interface LoadAttendancePort {

    fun findByUserIdAndCheckIn(userId: UUID): Attendance?
    fun findFirstByUserIdOrderByCreatedDateDesc(userId: UUID): Attendance?
    fun findALLByUserIdOrderByCreatedDateDesc(userId: UUID): List<Attendance>
}