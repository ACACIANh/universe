package kr.bomiza.universe.meeting.application.service

import kr.bomiza.universe.common.util.UserContextUtils
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.business.meeting.application.port.`in`.CreateMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.`in`.FindMeetingUseCase
import kr.bomiza.universe.business.security.domain.Authorities
import kr.bomiza.universe.business.security.domain.SecurityUser
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

//TODO: 테스트 코드 개선
@SpringBootTest
class MeetingServiceTest(
    @Autowired val createMeetingUseCase: CreateMeetingUseCase,
    @Autowired val findMeetingUseCase: FindMeetingUseCase,
) {
    companion object {
        @BeforeAll
        @JvmStatic
        fun 시큐리티등록() {
            SecurityUser(
                UUID.fromString("feb6d5cb-c348-435f-a766-f2ea606ee6b4"),
                "gkehdrn95@naver.com",
                Authorities("ADMIN")
            ).let { UserContextUtils.setSecurityUser(it) }
        }
    }

    @Test
    @Transactional
    fun 정모페이지적용() {
        //given
        val startIndex = 1
        val inputDataSize = 11
        val pageSize = inputDataSize + 2
        val currentUser = UserContextUtils.getCurrentUser()
        for (i in startIndex..inputDataSize) {
            val date = LocalDate.of(2023, 12, i)
            val meetingCreateRequestDto = MeetingCreateRequestDto(date, 16)
            createMeetingUseCase.createMeeting(currentUser.id, meetingCreateRequestDto)
        }

        //when
        val pageRequest = PageRequest.of(0, pageSize)
        val findAll = findMeetingUseCase.findAll(pageRequest)

        //then
        Assertions.assertThat(findAll.size).isEqualTo(inputDataSize)
        Assertions.assertThat(findAll.first().date).isAfter(findAll.last().date)
    }
}