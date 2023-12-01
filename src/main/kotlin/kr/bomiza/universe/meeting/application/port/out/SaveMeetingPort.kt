package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.Meeting

interface SaveMeetingPort {

    fun saveMeeting(meeting: Meeting)
}