package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.domain.meeting.model.Meeting

interface SaveMeetingPort {

    fun saveMeeting(meeting: Meeting)
}