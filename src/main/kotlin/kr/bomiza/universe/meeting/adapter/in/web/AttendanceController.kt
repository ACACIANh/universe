package kr.bomiza.universe.meeting.adapter.`in`.web

import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.AttendanceRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.AttendanceResponseDto
import kr.bomiza.universe.meeting.adapter.`in`.web.swagger.IAttendanceController
import kr.bomiza.universe.meeting.application.legacy.AttendanceService
import kr.bomiza.universe.security.domain.SecurityUser
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AttendanceController(
    val attendanceService: AttendanceService
) : IAttendanceController {

    @PostMapping("/api/v1/attendances")
    override fun attendance(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestBody attendanceRequestDto: AttendanceRequestDto
    ): ResponseEntity<Unit> {

        attendanceService.attendance(securityUser.id, attendanceRequestDto.checkIn)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/api/v1/attendances/last")
    override fun findLastAttendance(
        @AuthenticationPrincipal securityUser: SecurityUser

    ): ResponseEntity<AttendanceResponseDto> {

        val attendanceResponseDto = attendanceService.findLastAttendance(securityUser.id)

        return ResponseEntity.ok(attendanceResponseDto)
    }

    @GetMapping("/api/v1/attendances")
    override fun findAllAttendance(
        @AuthenticationPrincipal securityUser: SecurityUser
    ): ResponseEntity<List<AttendanceResponseDto>> {

        val attendanceResponseDto = attendanceService.findAllAttendance(securityUser.id)

        return ResponseEntity.ok(attendanceResponseDto)
    }
}