package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.domain.meeting.model.MeetingUser
import java.util.*

interface LoadMeetingUserPort {

    fun loadMeetingUser(meetingUserId: UUID): MeetingUser
}