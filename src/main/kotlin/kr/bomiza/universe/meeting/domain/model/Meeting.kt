package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.common.Base
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class Meeting(
    id: UUID,
    var masterUser: User,
    var date: LocalDate,
    var capacityMember: Int,
    val meetingUsers: MeetingUsers,
) : Base(id) {

    constructor(masterUser: User, date: LocalDate, capacityMember: Int, meetingUsers: MeetingUsers) :
            this(newInstance(), masterUser, date, capacityMember, meetingUsers)

    fun joinMeeting(user: User, joinTime: LocalTime, isGuest: Boolean): MeetingUser {
        return meetingUsers.join(this, user, joinTime, isGuest)
    }

    fun refreshUsers() {
        meetingUsers.refreshUsers()
    }
}