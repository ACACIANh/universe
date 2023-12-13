package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.domain.common.UniverseException
import java.net.HttpURLConnection

class NotFoundUserException(
    private val userId: String,
) : UniverseException(
    "Not found userId : $userId",
    HttpURLConnection.HTTP_NOT_FOUND,
    null,
    null
) {
}