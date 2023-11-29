package kr.bomiza.universe.common.model.exception

import org.springframework.http.HttpStatus

class NotFoundAdminUserException(
) : UniverseException(
    "Not found admin user ",
    HttpStatus.NOT_FOUND,
    null,
    null
) {
}