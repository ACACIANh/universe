package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.Meeting
import org.springframework.data.domain.Pageable
import java.util.*

interface LoadMeetingPort {

    fun findAll(page: Pageable): List<Meeting>
    fun loadMeeting(meetingId: UUID): Meeting
}