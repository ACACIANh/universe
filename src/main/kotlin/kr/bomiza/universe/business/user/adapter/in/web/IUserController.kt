package kr.bomiza.universe.business.user.adapter.`in`.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.bomiza.universe.business.user.adapter.`in`.web.model.response.UserResponseDto
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

@Tag(name = "User", description = "회원")
@SecurityRequirement(name = "Authorization")
interface IUserController {

    @Operation(summary = "회원 조회", description = "회원 조회 설명")
    fun findAllAttendance(
        page: Pageable
    ): ResponseEntity<List<UserResponseDto>>

    @Operation(summary = "회원 활성 비활성", description = "회원 활성 비활성 설명")
    fun inactiveUser(
        @PathVariable userId: UUID,
    ): ResponseEntity<UserResponseDto>
}