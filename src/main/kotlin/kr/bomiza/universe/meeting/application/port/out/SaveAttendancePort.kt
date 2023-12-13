package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.domain.meeting.model.Attendance

interface SaveAttendancePort {

    fun saveAttendance(attendance: Attendance)
}