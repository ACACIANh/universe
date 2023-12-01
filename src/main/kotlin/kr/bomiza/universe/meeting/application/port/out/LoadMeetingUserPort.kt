package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.MeetingUser
import java.util.*

interface LoadMeetingUserPort {

    fun loadMeetingUser(meetingUserId: UUID): MeetingUser
}