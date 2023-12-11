package kr.bomiza.universe.meeting.application.port.`in`

import kr.bomiza.universe.meeting.domain.model.Attendance
import org.springframework.data.domain.Pageable
import java.util.*

interface FindAllAttendanceUseCase {

    fun findAllAttendance(userId: UUID, page: Pageable): List<Attendance>
}