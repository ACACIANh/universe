package kr.bomiza.universe.meeting.adapter.`in`.web.swagger

import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "Admin", description = "관리자")
@SecurityRequirement(name = "Authorization")
interface IAdminController {

    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    @Operation(summary = "관리자승급", description = "관리자승급 설명")
    fun userUpdateAdmin(): ResponseEntity<Unit>

    @Hidden
    fun token(@RequestParam("token") token: String): String?
}