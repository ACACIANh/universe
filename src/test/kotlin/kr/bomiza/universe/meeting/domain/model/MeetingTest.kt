package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.common.enums.MDCKeys
import kr.bomiza.universe.meeting.application.legacy.CAPACITY_MEMBER
import kr.bomiza.universe.meeting.domain.enums.MeetingUserState
import kr.bomiza.universe.meeting.domain.enums.UserRole
import kr.bomiza.universe.meeting.domain.exception.AlreadyJoinException
import kr.bomiza.universe.security.domain.UserState
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.MDC
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.stream.IntStream

class MeetingTest {

    private lateinit var adminUser: User
    private lateinit var meetingUsers: MeetingUsers
    private lateinit var meeting: Meeting

    @BeforeEach
    fun 미팅준비() {
        MDC.put(MDCKeys.REQUEST_TIME.name, LocalDateTime.now().toString())

        adminUser = User("하동구", UserState.ACTIVATE, UserRole.ADMIN)
        meetingUsers = MeetingUsers()
        meeting = Meeting(adminUser, LocalDate.now(), CAPACITY_MEMBER, meetingUsers)
    }

    @Test
    fun 미팅에참여한다() {
        //given
        val joinMember = User("정한재", UserState.ACTIVATE, UserRole.MEMBER)
        val isGuest = false
        val joinTime = LocalTime.now()

        //when
        val meetingUser = meetingUsers.join(meeting, joinMember, joinTime, isGuest)

        //then
        Assertions.assertThat(meetingUser.user.id).isEqualTo(joinMember.id)
        Assertions.assertThat(meetingUser.joinTime).isEqualTo(joinTime)
        Assertions.assertThat(meetingUser.guest).isEqualTo(isGuest)
        Assertions.assertThat(meetingUser.state).isEqualTo(MeetingUserState.PARTICIPATION)
        Assertions.assertThat(meetingUsers.currentUserCount()).isEqualTo(1)
    }

    @Test
    fun 이미참여중이라면예외가발생한다() {
        //given
        val joinMember = User("정한재", UserState.ACTIVATE, UserRole.MEMBER)
        val isGuest = false
        val joinTime = LocalTime.now()

        meetingUsers.join(meeting, joinMember, joinTime, isGuest)
        //when
        //then
        Assertions.assertThatThrownBy {
            meetingUsers.join(meeting, joinMember, joinTime, isGuest)
        }.isInstanceOf(AlreadyJoinException::class.java)
    }

    @Test
    fun 미팅인원이넘어가면참여자는대기상태가된다() {
        //given
        IntStream.rangeClosed(1, CAPACITY_MEMBER).forEach {
            val joinMember = User("정한재$it", UserState.ACTIVATE, UserRole.MEMBER)
            val isGuest = false
            val joinTime = LocalTime.now()
            meetingUsers.join(meeting, joinMember, joinTime, isGuest)
        }
        val joinMember = User("정한재", UserState.ACTIVATE, UserRole.MEMBER)
        val isGuest = false
        val joinTime = LocalTime.now()

        //when
        val meetingUser = meetingUsers.join(meeting, joinMember, joinTime, isGuest)

        //then
        Assertions.assertThat(meetingUser.state).isEqualTo(MeetingUserState.WAITING)
        Assertions.assertThat(meetingUsers.currentUserCount()).isEqualTo(CAPACITY_MEMBER + 1)
    }
}