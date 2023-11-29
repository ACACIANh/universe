package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class NotFoundAdminUserException(
) : UniverseException(
    "Not found admin user ",
    HttpStatus.NOT_FOUND,
    null,
    null
) {
}