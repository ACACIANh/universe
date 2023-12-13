package kr.bomiza.universe.domain.meeting.model

import kr.bomiza.universe.domain.common.model.Base
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class Meeting(
    id: UUID,
    var masterUser: User,
    var date: LocalDate,
    val meetingUsers: MeetingUsers,
) : Base(id) {

    constructor(masterUser: User, date: LocalDate, capacityMember: Int) :
            this(newInstance(), masterUser, date, MeetingUsers(capacityMember))

    fun joinMeeting(user: User, joinTime: LocalTime, isGuest: Boolean): MeetingUser {
        return meetingUsers.join(this, user, joinTime, isGuest)
    }

    fun refreshUsers() {
        meetingUsers.refreshUsers()
    }
}