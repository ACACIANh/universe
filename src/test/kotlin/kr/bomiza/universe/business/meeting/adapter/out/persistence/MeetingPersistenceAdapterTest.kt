package kr.bomiza.universe.business.meeting.adapter.out.persistence

import kr.bomiza.universe.common.enums.MDCKeys
import kr.bomiza.universe.common.enums.UserRole
import kr.bomiza.universe.common.enums.UserState
import kr.bomiza.universe.domain.meeting.exception.NotFoundMeetingUserException
import kr.bomiza.universe.domain.meeting.model.Meeting
import kr.bomiza.universe.domain.meeting.model.User
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@SpringBootTest
class MeetingPersistenceAdapterTest(
    @Autowired val meetingPersistenceAdapter: MeetingPersistenceAdapter
) {
    private val CAPACITY_MEMBER: Int = 16
    private lateinit var adminUser: User
    private lateinit var meeting: Meeting

    @BeforeEach
    fun 미팅준비() {
        MDC.put(MDCKeys.REQUEST_TIME.name, LocalDateTime.now().toString())

        adminUser =
            User(UUID.fromString("feb6d5cb-c348-435f-a766-f2ea606ee6b4"), "하동구", UserState.ACTIVATE, UserRole.ADMIN)
        meeting = Meeting(adminUser, LocalDate.now(), CAPACITY_MEMBER)
    }

    @Test
    @Transactional
    fun 미팅삽입및조회() {
        //given
        meetingPersistenceAdapter.saveMeeting(meeting)
        val meetingUser = meeting.joinMeeting(adminUser, LocalTime.now(), false)
        meetingPersistenceAdapter.saveMeetingUser(meetingUser)

        //when
        val loadMeetingUser = meetingPersistenceAdapter.loadMeetingUser(meetingUser.id)
        val loadMeetingUsersByUserId =
            meetingPersistenceAdapter.loadMeetingUserByUserIdAndDate(adminUser.id, LocalDate.now())

        //then
        Assertions.assertThat(
            meetingPersistenceAdapter.loadMeetingUserByUserIdAndDate(
                adminUser.id,
                LocalDate.now().minusDays(1)
            )
        ).isEmpty()
        Assertions.assertThat(loadMeetingUser.id).isEqualTo(meetingUser.id)
        loadMeetingUsersByUserId.forEach {
            Assertions.assertThat(it.id).isEqualTo(meetingUser.id)
        }
    }
}
