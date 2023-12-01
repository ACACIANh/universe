package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.common.Base
import kr.bomiza.universe.common.util.TimeUtils
import java.time.LocalDateTime
import java.util.*

class Attendance(
    override val id: UUID,
    var user: User,
    var checkIn: LocalDateTime?,
    var checkOut: LocalDateTime?,
) : Base(id) {

    companion object {
        fun checkIn(user: User): Attendance {
            return Attendance(newInstance(), user, TimeUtils.currentTime(), null)
        }
    }

    fun checkOut() {
        this.checkOut = TimeUtils.currentTime()
    }
}