package kr.bomiza.universe.common.model.exception

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