package kr.bomiza.universe.security.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class NotExistAuthorizationHeader(
) : UniverseException(
    "Not exist authorization header",
    HttpStatus.BAD_REQUEST,
    null,
    null
) {
}