package kr.bomiza.universe.common.model.exception.auth

import kr.bomiza.universe.common.model.exception.UniverseException
import org.springframework.http.HttpStatus

class NotExistToken(
) : UniverseException(
    "Not exist token",
    HttpStatus.BAD_REQUEST,
    null,
    null
) {
}