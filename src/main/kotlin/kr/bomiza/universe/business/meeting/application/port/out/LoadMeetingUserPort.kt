package kr.bomiza.universe.business.meeting.application.port.out

import kr.bomiza.universe.domain.meeting.model.MeetingUser
import java.time.LocalDate
import java.util.*

interface LoadMeetingUserPort {

    fun loadMeetingUser(meetingUserId: UUID): MeetingUser
    fun loadMeetingUserByUserIdAndDate(userId: UUID, localDate: LocalDate): List<MeetingUser>
}