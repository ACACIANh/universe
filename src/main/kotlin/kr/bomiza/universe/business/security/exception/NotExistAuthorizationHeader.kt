package kr.bomiza.universe.business.security.exception

import kr.bomiza.universe.common.exception.UniverseException
import org.springframework.http.HttpStatus

class NotExistAuthorizationHeader(
) : UniverseException(
    "Not exist authorization header",
    HttpStatus.BAD_REQUEST.value(),
    null,
    null
) {
}