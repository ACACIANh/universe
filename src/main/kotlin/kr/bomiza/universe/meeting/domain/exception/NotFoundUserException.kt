package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class NotFoundUserException(
    private val userId: String,
) : UniverseException(
    "Not found userId : $userId",
    HttpStatus.NOT_FOUND,
    null,
    null
) {
}