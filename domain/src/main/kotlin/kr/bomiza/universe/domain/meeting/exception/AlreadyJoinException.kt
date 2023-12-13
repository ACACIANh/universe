package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.domain.common.exception.UniverseException
import java.net.HttpURLConnection

class AlreadyJoinException(
    private val meetingId: String,
    private val memberId: String,
    private val guest: String,
) : UniverseException(
    "Already join meetingId : $meetingId, memberId : $memberId, isGuest: $guest",
    HttpURLConnection.HTTP_BAD_REQUEST,
    null,
    null
) {
}