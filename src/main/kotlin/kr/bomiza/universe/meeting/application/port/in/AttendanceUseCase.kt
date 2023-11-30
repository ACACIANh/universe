package kr.bomiza.universe.meeting.application.port.`in`

import java.util.*

interface AttendanceUseCase {

    fun attendance(userId: UUID, checkIn: Boolean)
}