package kr.bomiza.universe.security.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class NotExistToken(
) : UniverseException(
    "Not exist token",
    HttpStatus.BAD_REQUEST,
    null,
    null
) {
}