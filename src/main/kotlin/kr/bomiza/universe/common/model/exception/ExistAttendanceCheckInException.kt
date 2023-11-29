package kr.bomiza.universe.common.model.exception

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