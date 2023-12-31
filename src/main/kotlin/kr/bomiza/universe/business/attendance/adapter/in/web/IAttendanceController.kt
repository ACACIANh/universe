package kr.bomiza.universe.business.attendance.adapter.`in`.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.bomiza.universe.business.attendance.adapter.`in`.web.model.request.AttendanceRequestDto
import kr.bomiza.universe.business.attendance.adapter.`in`.web.model.response.AttendanceResponseDto
import kr.bomiza.universe.domain.security.model.SecurityUser
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "Attendance", description = "출석")
@SecurityRequirement(name = "Authorization")
interface IAttendanceController {

    @Operation(summary = "출석체크", description = "출석체크 설명")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        ApiResponse(responseCode = "404", description = "NOT FOUND"),
        ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    )
    fun attendance(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestBody attendanceRequestDto: AttendanceRequestDto
    ): ResponseEntity<Unit>

    @Operation(summary = "마지막 접속 확인", description = "마지막접속확인 설명")
    fun findLastAttendance(
        @AuthenticationPrincipal securityUser: SecurityUser
    ): ResponseEntity<AttendanceResponseDto>

    @Operation(summary = "사용자 접속기록 확인", description = "사용자 접속기록 확인 설명")
    fun findAllAttendance(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @ParameterObject page: Pageable
    ): ResponseEntity<List<AttendanceResponseDto>>
}