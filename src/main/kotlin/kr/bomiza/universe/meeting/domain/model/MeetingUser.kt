package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.common.Base
import kr.bomiza.universe.common.util.TimeUtils
import kr.bomiza.universe.meeting.domain.enums.MeetingUserState
import kr.bomiza.universe.meeting.domain.exception.InvalidAccessResourceException
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class MeetingUser(
    override val id: UUID,
    var meeting: Meeting,
    var user: User,
    var state: MeetingUserState,
    var joinTime: LocalTime,
    var guest: Boolean,
    var createdDate: LocalDateTime,
) : Base(id) {

    constructor(meeting: Meeting, user: User, state: MeetingUserState, joinTime: LocalTime, guest: Boolean) :
            this(newInstance(), meeting, user, state, joinTime, guest, TimeUtils.currentTime())

    fun updateUserDate(user: User, joinTime: LocalTime, guest: Boolean) {
        // todo: kotlin 문법 점검
        if (!id.equals(user.id)) {
            throw InvalidAccessResourceException(id, user.id)
        }
        this.joinTime = joinTime
        this.guest = guest
    }
}