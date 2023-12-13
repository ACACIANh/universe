package kr.bomiza.universe.security.exception

import kr.bomiza.universe.domain.common.UniverseException
import org.springframework.http.HttpStatus

class NotExistToken(
) : UniverseException(
    "Not exist token",
    HttpStatus.BAD_REQUEST.value(),
    null,
    null
) {
}