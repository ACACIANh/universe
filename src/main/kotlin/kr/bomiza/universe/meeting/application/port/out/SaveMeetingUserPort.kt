package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.MeetingUser

interface SaveMeetingUserPort {

    fun saveMeetingUser(meetingUser: MeetingUser)
}