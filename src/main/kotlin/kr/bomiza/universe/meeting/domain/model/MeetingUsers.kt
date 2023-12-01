package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.meeting.application.legacy.CAPACITY_MEMBER
import kr.bomiza.universe.meeting.domain.enums.MeetingUserState
import kr.bomiza.universe.meeting.domain.exception.AlreadyJoinException
import java.time.LocalTime

class MeetingUsers(
    val meetingUsers: List<MeetingUser>
) {
    constructor() : this(ArrayList<MeetingUser>())
    constructor(vararg meetingUsers: MeetingUser) : this(meetingUsers.toList())

    fun join(meeting: Meeting, user: User, joinTime: LocalTime, isGuest: Boolean): MeetingUser {
        meetingUsers.stream()
            .filter { e -> alreadyJoinCheck(e, user, isGuest) }
            .anyMatch { throw AlreadyJoinException(it.id.toString(), user.id.toString(), isGuest.toString()) }

        val meetingUser = MeetingUser(meeting, user, stateCheck(), joinTime, isGuest)
        meetingUsers.plus(meetingUser)
        //todo: plus 하고 size 가 0 나오는데 이유찾기 (될때도있는데 안되는 상황 찾아보기)
        return meetingUser
    }

    // todo: == 비교 맞는지 확인 후 수정
    private fun alreadyJoinCheck(e: MeetingUser, user: User, isGuest: Boolean) =
        e.user.id == user.id && e.guest == isGuest

    private fun stateCheck(): MeetingUserState {
        return if (meetingUsers.size < CAPACITY_MEMBER) MeetingUserState.PARTICIPATION else MeetingUserState.WAITING
    }

    fun refreshUsers() {
        // todo: 메소드 분리? 검토
        meetingUsers.stream()
            .filter {
                it.state == MeetingUserState.PARTICIPATION || it.state == MeetingUserState.WAITING
            }
            .sorted(Comparator.comparing(MeetingUser::createdDate))
            .limit(CAPACITY_MEMBER.toLong())
            .filter { it.state == MeetingUserState.WAITING }
            .map { it.state = MeetingUserState.PARTICIPATION }
    }
}