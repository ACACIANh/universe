package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.common.exception.UniverseException
import java.net.HttpURLConnection

class NotFoundMeetingUserException(
    private val meetingUserId: String = "",
    private val userId: String = "",
) : UniverseException(
    "Not found meetingUserId : $meetingUserId, userId : $userId",
    HttpURLConnection.HTTP_NOT_FOUND,
    null,
    null
) {
}