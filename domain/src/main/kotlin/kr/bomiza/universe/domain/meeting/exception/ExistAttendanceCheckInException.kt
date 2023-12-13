package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.domain.common.UniverseException
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