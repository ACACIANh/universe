package kr.bomiza.universe.meeting.application.port.`in`

import kr.bomiza.universe.meeting.domain.model.Attendance
import java.util.*

interface FindAllAttendanceUseCase {

    fun findAllAttendance(userId: UUID): List<Attendance>
}