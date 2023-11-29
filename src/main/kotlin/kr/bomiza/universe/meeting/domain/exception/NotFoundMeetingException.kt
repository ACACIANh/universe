package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
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