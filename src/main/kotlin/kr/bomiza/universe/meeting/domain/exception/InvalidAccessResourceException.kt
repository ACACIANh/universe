package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus
import java.util.*

class InvalidAccessResourceException(
    private val ownerId: UUID,
    private val userId: UUID,
) : UniverseException(
    "Invalid access - resource ownerId : $ownerId, access userId : $userId",
    HttpStatus.UNAUTHORIZED,
    null,
    null
) {
}