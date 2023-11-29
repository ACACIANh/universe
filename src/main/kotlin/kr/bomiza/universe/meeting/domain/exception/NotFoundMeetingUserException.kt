package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class NotFoundMeetingUserException(
    private val meetingUserId: String,
) : UniverseException(
    "Not found meetingUserId : $meetingUserId",
    HttpStatus.NOT_FOUND,
    null,
    null
) {
}