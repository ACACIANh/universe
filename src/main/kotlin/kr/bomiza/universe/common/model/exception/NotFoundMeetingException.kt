package kr.bomiza.universe.common.model.exception

import org.springframework.http.HttpStatus

class NotFoundMeetingException(
    private val meetingId: String,
) : UniverseException(
    "Not found meetingId : $meetingId",
    HttpStatus.NOT_FOUND,
    null,
    null
) {
}