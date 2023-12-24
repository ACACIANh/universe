package kr.bomiza.universe.business.meeting.application.port.`in`

import java.time.LocalDate
import java.util.UUID

interface FinishMeetingUseCase {

    fun finish(userId: UUID, date: LocalDate)
}