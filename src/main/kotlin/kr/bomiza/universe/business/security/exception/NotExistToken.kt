package kr.bomiza.universe.business.security.exception

import kr.bomiza.universe.common.exception.UniverseException
import org.springframework.http.HttpStatus

class NotExistToken(
) : UniverseException(
    "Not exist token",
    HttpStatus.BAD_REQUEST.value(),
    null,
    null
) {
}