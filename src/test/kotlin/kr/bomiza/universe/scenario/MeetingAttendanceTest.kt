package kr.bomiza.universe.scenario

import kr.bomiza.universe.business.attendance.application.service.AttendanceService
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.business.meeting.application.service.MeetingService
import kr.bomiza.universe.common.enums.MDCKeys
import kr.bomiza.universe.common.util.UserContextUtils
import kr.bomiza.universe.domain.meeting.enums.MeetingUserState
import kr.bomiza.universe.domain.security.model.Authorities
import kr.bomiza.universe.domain.security.model.SecurityUser
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@SpringBootTest
class MeetingAttendanceTest(
    @Autowired val meetingService: MeetingService,
    @Autowired val attendanceService: AttendanceService
) {
    companion object {
        @BeforeAll
        @JvmStatic
        fun 시큐리티등록_MDC추가() {
            SecurityUser(
                UUID.fromString("feb6d5cb-c348-435f-a766-f2ea606ee6b4"),
                "gkehdrn95@naver.com",
                Authorities("ADMIN")
            ).let { UserContextUtils.setSecurityUser(it) }

            MDC.put(MDCKeys.REQUEST_TIME.name, LocalDateTime.now().toString())
        }
    }

    // 1. meeting 생성
    // 2. meeting 참여
    // 3. 출석 in
    // 4. 출석 out
    @Test
    @Order(1)
    fun 미팅출석연동() {
        // 1. meeting 생성
        val currentUser = UserContextUtils.getCurrentUser()
        val date = LocalDate.now()
        val meetingCreateRequestDto = MeetingCreateRequestDto(date, 16)
        val createdMeeting = meetingService.createMeeting(currentUser.id, meetingCreateRequestDto)
        // 2. meeting 참여
        val meetingJoinRequestDto = MeetingJoinRequestDto(false, LocalTime.of(15, 30))
        val joinedMeeting = meetingService.joinMeeting(currentUser.id, createdMeeting.id, meetingJoinRequestDto)
        // 3. 출석 in
        attendanceService.attendance(currentUser.id, true)
        // 4. 출석 out
        attendanceService.attendance(currentUser.id, false)

//        meetingService.findAll(Pageable.unpaged())
//            .first()
//            .meetingUsers.meetingUsers.forEach {
//                Assertions.assertThat(it.id).isEqualTo(joinedMeeting.id)
//                Assertions.assertThat(it.state).isEqualTo(MeetingUserState.FINISHED)
//            }
    }

    @Test
    @Order(2)
    fun 조회() {
        // 흠냐링;
        Thread.sleep(200) //0.1초는 실패
        meetingService.findAll(Pageable.unpaged())
            .first()
            .meetingUsers.meetingUsers.forEach {
                Assertions.assertThat(it.state).isEqualTo(MeetingUserState.FINISHED)
            }
    }

}