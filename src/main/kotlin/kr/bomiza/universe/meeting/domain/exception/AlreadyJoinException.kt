package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class AlreadyJoinException(
    private val meetingId: String,
    private val memberId: String,
    private val guest: String,
) : UniverseException(
    "Already join meetingId : $meetingId, memberId : $memberId, isGuest: $guest",
    HttpStatus.BAD_REQUEST,
    null,
    null
) {
}