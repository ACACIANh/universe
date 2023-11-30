package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.Attendance

interface SaveAttendancePort {

    fun saveAttendance(attendance: Attendance)
}