package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class ExistAttendanceCheckInException(
    private val attendanceId: String
) : UniverseException(
    "Exist Attendance CheckIn attendanceID : $attendanceId ",
    HttpStatus.BAD_REQUEST,
    null,
    null
) {
}