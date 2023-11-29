package kr.bomiza.universe.common.model.exception

import org.springframework.http.HttpStatus

class SomethingWrongException(
) : UniverseException(
    "Something Wrong",
    HttpStatus.NOT_FOUND,
    null,
    null
) {
}