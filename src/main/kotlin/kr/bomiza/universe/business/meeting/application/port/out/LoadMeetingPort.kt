package kr.bomiza.universe.business.meeting.application.port.out

import kr.bomiza.universe.domain.meeting.model.Meeting
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.util.*

interface LoadMeetingPort {

    fun findAll(page: Pageable): List<Meeting>
    fun loadMeeting(meetingId: UUID): Meeting
    fun loadMeeting(localDate: LocalDate): Meeting?
}