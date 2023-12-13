package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.common.exception.UniverseException
import java.net.HttpURLConnection

class NotFoundAdminUserException(
) : UniverseException(
    "Not found admin user ",
    HttpURLConnection.HTTP_NOT_FOUND,
    null,
    null
) {
}