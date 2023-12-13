package kr.bomiza.universe.domain.attendance.exception

import kr.bomiza.universe.domain.common.exception.UniverseException
import java.net.HttpURLConnection

class ExistAttendanceCheckInException(
    private val attendanceId: String
) : UniverseException(
    "Exist Attendance CheckIn attendanceID : $attendanceId ",
    HttpURLConnection.HTTP_BAD_REQUEST,
    null,
    null
) {
}