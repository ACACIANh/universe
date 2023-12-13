package kr.bomiza.universe.domain.meeting.model

import kr.bomiza.universe.common.model.Base
import kr.bomiza.universe.domain.meeting.enums.MeetingUserState
import kr.bomiza.universe.domain.meeting.exception.InvalidAccessResourceException
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class MeetingUser(
    id: UUID,
    val meeting: Meeting,
    val user: User,
    var state: MeetingUserState,
    var joinTime: LocalTime,
    var guest: Boolean,
    val createdDate: LocalDateTime?,
) : Base(id) {

    constructor(meeting: Meeting, user: User, state: MeetingUserState, joinTime: LocalTime, guest: Boolean) :
            this(newInstance(), meeting, user, state, joinTime, guest, null)

    fun updateUserDate(user: User, joinTime: LocalTime, guest: Boolean) {
        if (id != user.id) {
            throw InvalidAccessResourceException(id, user.id)
        }
        this.joinTime = joinTime
        this.guest = guest
    }
}