package kr.bomiza.universe.business.meeting.adapter.`in`.event

import kr.bomiza.universe.business.meeting.application.port.out.LoadMeetingPort
import kr.bomiza.universe.business.meeting.application.port.out.SaveMeetingPort
import kr.bomiza.universe.domain.attendance.event.AttendanceEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import java.time.LocalDate

@Component
@Transactional
class MeetingEventListener(
    val loadMeetingPort: LoadMeetingPort,
    val saveMeetingPort: SaveMeetingPort
) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun attendance(event: AttendanceEvent) {
        val meeting = loadMeetingPort.loadMeeting(LocalDate.now())?.also {
            it.refreshUsers()
        } ?: return
        saveMeetingPort.saveMeeting(meeting)
    }
}