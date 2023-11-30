package kr.bomiza.universe.meeting.application.port.`in`

import kr.bomiza.universe.meeting.domain.model.Attendance
import java.util.*

interface FindLastAttendanceUseCase {

    fun findLastAttendance(userId: UUID): Attendance
}