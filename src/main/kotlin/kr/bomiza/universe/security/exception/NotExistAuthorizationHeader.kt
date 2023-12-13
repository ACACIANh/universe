package kr.bomiza.universe.security.exception

import kr.bomiza.universe.domain.common.UniverseException
import org.springframework.http.HttpStatus

class NotExistAuthorizationHeader(
) : UniverseException(
    "Not exist authorization header",
    HttpStatus.BAD_REQUEST.value(),
    null,
    null
) {
}