package kr.bomiza.universe.domain.meeting.model

import kr.bomiza.universe.domain.meeting.enums.MeetingUserState
import kr.bomiza.universe.domain.meeting.exception.AlreadyJoinException
import java.time.LocalTime

class MeetingUsers(
    val capacity: Int,
    val meetingUsers: MutableList<MeetingUser>,
) {
    constructor(capacity: Int) : this(capacity, ArrayList<MeetingUser>())

    private val alreadyJoinCheck: (MeetingUser, User, Boolean) -> Boolean = { meetingUser, user, isGuest ->
        meetingUser.user.id == user.id && meetingUser.guest == isGuest
    }

    private fun stateCheck(): MeetingUserState {
        return if (meetingUsers.size < capacity) MeetingUserState.PARTICIPATION else MeetingUserState.WAITING
    }

    fun join(meeting: Meeting, user: User, joinTime: LocalTime, isGuest: Boolean): MeetingUser {
        meetingUsers.stream()
            .filter { alreadyJoinCheck(it, user, isGuest) }
            .anyMatch { throw AlreadyJoinException(it.id.toString(), user.id.toString(), isGuest.toString()) }

        val meetingUser = MeetingUser(meeting, user, stateCheck(), joinTime, isGuest)
        meetingUsers.add(meetingUser)
        return meetingUser
    }

    // todo: 위치 검토
    private val shouldPromoteToParticipation: (MeetingUser) -> Boolean = { user ->
        user.state == MeetingUserState.WAITING || user.state == MeetingUserState.PARTICIPATION
    }

    fun refreshUsers() {
        meetingUsers
            .filter(shouldPromoteToParticipation)
            .sortedBy(MeetingUser::createdDate)
            .take(capacity)
            .filter { it.state == MeetingUserState.WAITING }
            .forEach { it.state = MeetingUserState.PARTICIPATION }
    }

    fun currentUserCount(): Int = meetingUsers.count()
}