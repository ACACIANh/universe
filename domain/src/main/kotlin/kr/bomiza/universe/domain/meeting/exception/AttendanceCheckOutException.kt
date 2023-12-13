package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.domain.common.UniverseException
import java.net.HttpURLConnection

class AttendanceCheckOutException(
) : UniverseException(
    "Attendance CheckIn First",
    HttpURLConnection.HTTP_BAD_REQUEST,
    null,
    null
) {
}