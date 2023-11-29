package kr.bomiza.universe.common.model.exception

import org.springframework.http.HttpStatus

class NotFoundAttendanceException(
    private val userId: String
) : UniverseException(
    "Not found userId : $userId",
    HttpStatus.NOT_FOUND,
    null,
    null
) {

}
