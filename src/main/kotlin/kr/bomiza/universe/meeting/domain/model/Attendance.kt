package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.common.util.TimeUtils
import kr.bomiza.universe.common.util.UUIDUtils
import java.time.LocalDateTime
import java.util.*

class Attendance(
    val id: UUID,
    var user: User,
    var checkIn: LocalDateTime?,
    var checkOut: LocalDateTime?,
) {
    companion object {
        fun checkIn(user: User): Attendance {
            return Attendance(UUIDUtils.generate(), user, TimeUtils.currentTime(), null)
        }
    }

    fun checkOut() {
        this.checkOut = TimeUtils.currentTime()
    }
}