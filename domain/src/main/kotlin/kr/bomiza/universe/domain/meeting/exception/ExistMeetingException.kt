package kr.bomiza.universe.domain.meeting.exception

import kr.bomiza.universe.domain.common.exception.UniverseException
import java.net.HttpURLConnection

class ExistMeetingException(
    private val meetingId: String,
) : UniverseException(
    "Already exist meeting meetingId : $meetingId",
    HttpURLConnection.HTTP_BAD_REQUEST,
    null,
    null
) {
}