package kr.bomiza.universe.meeting.application.service

import kr.bomiza.universe.common.util.UserContextUtils
import kr.bomiza.universe.common.model.Base
import kr.bomiza.universe.domain.attendance.model.Attendance
import kr.bomiza.universe.business.attendance.application.port.`in`.FindAttendanceUseCase
import kr.bomiza.universe.business.attendance.application.port.out.LoadUserPort
import kr.bomiza.universe.business.attendance.application.port.out.SaveAttendancePort
import kr.bomiza.universe.domain.security.model.Authorities
import kr.bomiza.universe.domain.security.model.SecurityUser
import org.assertj.core.api.Assertions
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
    @Autowired val findAttendanceUseCase: FindAttendanceUseCase
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
        val findAll = findAttendanceUseCase.findAllAttendance(currentUser.id, pageRequest)

        //then
        Assertions.assertThat(findAll.size).isEqualTo(inputDataSize)
        Assertions.assertThat(findAll.first().checkIn).isAfter(findAll.last().checkIn)
    }
}