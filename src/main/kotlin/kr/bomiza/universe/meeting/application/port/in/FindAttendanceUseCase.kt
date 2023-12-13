package kr.bomiza.universe.meeting.application.port.`in`

import kr.bomiza.universe.domain.meeting.model.Attendance
import org.springframework.data.domain.Pageable
import java.util.*

interface FindAttendanceUseCase {

    fun findAllAttendance(userId: UUID, page: Pageable): List<Attendance>
    fun findLastAttendance(userId: UUID): Attendance
}