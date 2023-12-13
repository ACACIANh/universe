package kr.bomiza.universe.domain.attendance.exception

import kr.bomiza.universe.common.exception.UniverseException
import java.net.HttpURLConnection

class NotFoundAttendanceException(
    private val userId: String
) : UniverseException(
    "Not found userId : $userId",
    HttpURLConnection.HTTP_NOT_FOUND,
    null,
    null
) {

}
