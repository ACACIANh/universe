package kr.bomiza.universe.business.attendance.application.port.`in`

import java.util.*

interface AttendanceUseCase {

    fun attendance(userId: UUID, checkIn: Boolean)
}