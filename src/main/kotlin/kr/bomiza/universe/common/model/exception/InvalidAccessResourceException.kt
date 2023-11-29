package kr.bomiza.universe.common.model.exception

import org.springframework.http.HttpStatus

class InvalidAccessResourceException(
    private val ownerId: Long,
    private val userId: Long,
) : UniverseException(
    "Invalid access - resource ownerId : $ownerId, access userId : $userId",
    HttpStatus.UNAUTHORIZED,
    null,
    null
) {
}