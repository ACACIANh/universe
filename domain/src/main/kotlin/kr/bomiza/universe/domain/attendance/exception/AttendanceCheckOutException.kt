package kr.bomiza.universe.domain.attendance.exception

import kr.bomiza.universe.domain.common.exception.UniverseException
import java.net.HttpURLConnection

class AttendanceCheckOutException(
) : UniverseException(
    "Attendance CheckIn First",
    HttpURLConnection.HTTP_BAD_REQUEST,
    null,
    null
) {
}