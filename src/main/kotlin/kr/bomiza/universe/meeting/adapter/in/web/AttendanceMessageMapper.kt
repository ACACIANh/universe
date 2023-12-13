package kr.bomiza.universe.meeting.adapter.`in`.web

import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.AttendanceResponseDto
import kr.bomiza.universe.domain.meeting.model.Attendance
import org.springframework.stereotype.Component

@Component
class AttendanceMessageMapper {

    fun mapToDto(attendance: Attendance): AttendanceResponseDto {
        return AttendanceResponseDto(attendance.id, attendance.user.id, attendance.checkIn, attendance.checkOut)
    }

    fun mapToDto(attendance: List<Attendance>): List<AttendanceResponseDto> {
        return attendance.map { mapToDto(it) }
    }
}