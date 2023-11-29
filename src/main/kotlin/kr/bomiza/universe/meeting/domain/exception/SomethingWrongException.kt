package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class SomethingWrongException(
) : UniverseException(
    "Something Wrong",
    HttpStatus.NOT_FOUND,
    null,
    null
) {
}