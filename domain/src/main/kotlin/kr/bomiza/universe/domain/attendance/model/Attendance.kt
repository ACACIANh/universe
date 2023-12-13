package kr.bomiza.universe.domain.attendance.model

import kr.bomiza.universe.domain.common.model.Base
import java.time.LocalDateTime
import java.util.*

class Attendance(
    id: UUID,
    val user: User,
    val checkIn: LocalDateTime,
    var checkOut: LocalDateTime?,
) : Base(id) {

    companion object {
        fun checkIn(user: User, checkIn: LocalDateTime): Attendance {
            return Attendance(newInstance(), user, checkIn, null)
        }
    }

    fun checkOut(checkOut: LocalDateTime) {
        this.checkOut = checkOut
    }
}