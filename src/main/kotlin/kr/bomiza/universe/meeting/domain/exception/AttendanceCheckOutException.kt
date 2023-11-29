package kr.bomiza.universe.meeting.domain.exception

import kr.bomiza.universe.common.UniverseException
import org.springframework.http.HttpStatus

class AttendanceCheckOutException(
) : UniverseException(
    "Attendance CheckIn First",
    HttpStatus.BAD_REQUEST,
    null,
    null
) {
}