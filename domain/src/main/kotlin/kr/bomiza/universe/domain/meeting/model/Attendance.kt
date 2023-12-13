package kr.bomiza.universe.domain.meeting.model

import kr.bomiza.universe.domain.common.Base
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