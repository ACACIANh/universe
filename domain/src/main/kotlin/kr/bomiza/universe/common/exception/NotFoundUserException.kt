package kr.bomiza.universe.common.exception

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