package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.common.exception.UniverseException
import java.net.HttpURLConnection

class NotFoundMeetingException(
    private val meetingId: String,
) : UniverseException(
    "Not found meetingId : $meetingId",
    HttpURLConnection.HTTP_NOT_FOUND,
    null,
    null
) {
}