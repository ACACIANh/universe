package kr.bomiza.universe.domain.attendance.event

import kr.bomiza.universe.common.model.Event
import java.util.UUID

class AttendanceEvent(
    val userId: UUID
) : Event() {
}