package kr.bomiza.universe.meeting.application.service

import kr.bomiza.universe.common.Base
import kr.bomiza.universe.common.util.UserContextUtils
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.meeting.application.port.`in`.FindAllAttendanceUseCase
import kr.bomiza.universe.meeting.application.port.out.LoadUserPort
import kr.bomiza.universe.meeting.application.port.out.SaveAttendancePort
import kr.bomiza.universe.meeting.domain.model.Attendance
import kr.bomiza.universe.security.domain.Authorities
import kr.bomiza.universe.security.domain.SecurityUser
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@SpringBootTest
class AttendanceServiceTest(
    @Autowired val loadUserPort: LoadUserPort,
    @Autowired val saveAttendancePort: SaveAttendancePort,
    @Autowired val findAllAttendanceUseCase: FindAllAttendanceUseCase
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
    fun 출석확인() {
        //given
        val startIndex = 1
        val inputDataSize = 11
        val pageSize = inputDataSize + 2
        val currentUser = UserContextUtils.getCurrentUser()
        val loadUser = loadUserPort.loadUser(currentUser.id)
        for (i in startIndex..inputDataSize) {
            val date = LocalDate.of(2023, 12, i)
            val checkInTime = LocalTime.of(10, 20)
            val checkIn = LocalDateTime.of(date, checkInTime)
            val checkOut = LocalDateTime.of(date, checkInTime.plusHours(1))
            val attendance = Attendance(Base.newInstance(), loadUser, checkIn, checkOut)
            saveAttendancePort.saveAttendance(attendance)
        }

        //when
        val pageRequest = PageRequest.of(0, pageSize)
        val findAll = findAllAttendanceUseCase.findAllAttendance(currentUser.id, pageRequest)

        //then
        Assertions.assertThat(findAll.size).isEqualTo(inputDataSize)
    }
}