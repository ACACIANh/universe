package kr.bomiza.universe.common.model.exception

import org.springframework.http.HttpStatus

class AttendanceCheckOutException(
) : UniverseException(
    "Attendance CheckIn First",
    HttpStatus.BAD_REQUEST,
    null,
    null
) {
}