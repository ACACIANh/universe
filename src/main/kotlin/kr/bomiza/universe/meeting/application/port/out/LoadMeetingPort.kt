package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.Meeting
import java.util.*

interface LoadMeetingPort {

    fun findAll(): List<Meeting>
    fun loadMeeting(meetingId: UUID): Meeting
}