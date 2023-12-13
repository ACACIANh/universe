package kr.bomiza.universe.business.attendance.application.port.out

import kr.bomiza.universe.domain.attendance.model.Attendance

interface SaveAttendancePort {

    fun saveAttendance(attendance: Attendance)
}