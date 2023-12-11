package kr.bomiza.universe.meeting.adapter.`in`.web

import kr.bomiza.universe.common.annotation.WebAdapter
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.AttendanceRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.AttendanceResponseDto
import kr.bomiza.universe.meeting.adapter.`in`.web.swagger.IAttendanceController
import kr.bomiza.universe.meeting.application.port.`in`.AttendanceUseCase
import kr.bomiza.universe.meeting.application.port.`in`.FindAllAttendanceUseCase
import kr.bomiza.universe.meeting.application.port.`in`.FindLastAttendanceUseCase
import kr.bomiza.universe.security.domain.SecurityUser
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
    val findAllAttendanceUseCase: FindAllAttendanceUseCase,
    val findLastAttendanceUseCase: FindLastAttendanceUseCase,
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

        val lastAttendance = findLastAttendanceUseCase.findLastAttendance(securityUser.id)
        val attendanceResponseDto = attendanceMessageMapper.mapToDto(lastAttendance)
        return ResponseEntity.ok(attendanceResponseDto)
    }

    @GetMapping("/api/v1/attendances")
    override fun findAllAttendance(
        @AuthenticationPrincipal securityUser: SecurityUser,
        page: Pageable
    ): ResponseEntity<List<AttendanceResponseDto>> {

        val attendances = findAllAttendanceUseCase.findAllAttendance(securityUser.id, page)
        val attendancesResponseDto = attendanceMessageMapper.mapToDto(attendances)
        return ResponseEntity.ok(attendancesResponseDto)
    }
}