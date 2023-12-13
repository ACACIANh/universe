package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.domain.common.UniverseException
import java.net.HttpURLConnection
import java.util.*

class InvalidAccessResourceException(
    private val ownerId: UUID,
    private val userId: UUID,
) : UniverseException(
    "Invalid access - resource ownerId : $ownerId, access userId : $userId",
    HttpURLConnection.HTTP_UNAUTHORIZED,
    null,
    null
) {
}