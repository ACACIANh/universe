package kr.bomiza.universe.common.model.exception.auth

import kr.bomiza.universe.common.model.exception.UniverseException
import org.springframework.http.HttpStatus

class NotExistAuthorizationHeader(
) : UniverseException(
    "Not exist authorization header",
    HttpStatus.BAD_REQUEST,
    null,
    null
) {
}