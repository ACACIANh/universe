package kr.bomiza.universe.web.controller

import kr.bomiza.universe.common.model.dto.attendance.AttendanceRequestDto
import kr.bomiza.universe.common.model.dto.attendance.AttendanceResponseDto
import kr.bomiza.universe.service.AttendanceService
import kr.bomiza.universe.common.util.UserContext
import org.springframework.http.ResponseEntity
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
        @RequestBody attendanceRequestDto: AttendanceRequestDto
    ): ResponseEntity<Unit> {

        val user = UserContext.getCurrentUser()

        attendanceService.attendance(user.id!!, attendanceRequestDto.checkIn)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/api/v1/attendances/last")
    override fun findLastAttendance(): ResponseEntity<AttendanceResponseDto> {

        val user = UserContext.getCurrentUser()

        val attendanceResponseDto = attendanceService.findLastAttendance(user.id!!)

        return ResponseEntity.ok(attendanceResponseDto)
    }

    @GetMapping("/api/v1/attendances/all")
    override fun findAllAttendance(): ResponseEntity<List<AttendanceResponseDto>> {

        val user = UserContext.getCurrentUser()

        val attendanceResponseDto = attendanceService.findAllAttendance(user.id!!)

        return ResponseEntity.ok(attendanceResponseDto)
    }
}