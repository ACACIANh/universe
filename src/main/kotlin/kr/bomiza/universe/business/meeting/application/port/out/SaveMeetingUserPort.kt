package kr.bomiza.universe.business.meeting.application.port.out

import kr.bomiza.universe.domain.meeting.model.MeetingUser

interface SaveMeetingUserPort {

    fun saveMeetingUser(meetingUser: MeetingUser)
}