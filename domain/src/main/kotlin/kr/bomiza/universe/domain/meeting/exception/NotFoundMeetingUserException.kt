package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.domain.common.UniverseException
import java.net.HttpURLConnection

class NotFoundMeetingUserException(
    private val meetingUserId: String,
) : UniverseException(
    "Not found meetingUserId : $meetingUserId",
    HttpURLConnection.HTTP_NOT_FOUND,
    null,
    null
) {
}