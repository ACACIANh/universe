package kr.bomiza.universe.business.attendance.adapter.`in`.web

import kr.bomiza.universe.business.attendance.adapter.`in`.web.model.request.AttendanceRequestDto
import kr.bomiza.universe.business.attendance.adapter.`in`.web.model.response.AttendanceResponseDto
import kr.bomiza.universe.business.attendance.application.port.`in`.AttendanceUseCase
import kr.bomiza.universe.business.attendance.application.port.`in`.FindAttendanceUseCase
import kr.bomiza.universe.business.security.domain.SecurityUser
import kr.bomiza.universe.common.annotation.WebAdapter
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class AttendanceController(
    val attendanceUseCase: AttendanceUseCase,
    val findAttendanceUseCase: FindAttendanceUseCase,
    val attendanceMessageMapper: AttendanceMessageMapper,
) : IAttendanceController {

    @PostMapping("/api/v1/attendances")
    override fun attendance(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestBody attendanceRequestDto: AttendanceRequestDto
    ): ResponseEntity<Unit> {

        attendanceUseCase.attendance(securityUser.id, attendanceRequestDto.checkIn)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/api/v1/attendances/last")
    override fun findLastAttendance(
        @AuthenticationPrincipal securityUser: SecurityUser
    ): ResponseEntity<AttendanceResponseDto> {

        val lastAttendance = findAttendanceUseCase.findLastAttendance(securityUser.id)
        val attendanceResponseDto = attendanceMessageMapper.mapToDto(lastAttendance)
        return ResponseEntity.ok(attendanceResponseDto)
    }

    @GetMapping("/api/v1/attendances")
    override fun findAllAttendance(
        @AuthenticationPrincipal securityUser: SecurityUser,
        page: Pageable
    ): ResponseEntity<List<AttendanceResponseDto>> {

        val attendances = findAttendanceUseCase.findAllAttendance(securityUser.id, page)
        val attendancesResponseDto = attendanceMessageMapper.mapToDto(attendances)
        return ResponseEntity.ok(attendancesResponseDto)
    }
}