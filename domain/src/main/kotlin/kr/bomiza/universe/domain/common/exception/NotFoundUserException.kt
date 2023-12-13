package kr.bomiza.universe.domain.common.exception

import kr.bomiza.universe.domain.common.exception.UniverseException
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