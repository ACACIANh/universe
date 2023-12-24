package kr.bomiza.universe.business.meeting.adapter.`in`.event

import kr.bomiza.universe.business.meeting.application.port.`in`.FinishMeetingUseCase
import kr.bomiza.universe.domain.attendance.event.AttendanceEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener
import java.time.LocalDate

@Component
class MeetingEventListener(
    val finishMeetingUseCase: FinishMeetingUseCase
) {
    @Async
    @EventListener
    fun attendance(event: AttendanceEvent) {
        finishMeetingUseCase.finish(event.userId, LocalDate.now())
    }
}