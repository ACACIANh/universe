package kr.bomiza.universe.business.security.web

import kr.bomiza.universe.business.security.domain.SecurityUser
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController(
    val securityUserService: SecurityUserService
) : IAdminController {

    @PostMapping("/api/v1/member/admin")
    override fun userUpdateAdmin(@AuthenticationPrincipal securityUser: SecurityUser): ResponseEntity<Unit> {

        securityUserService.updateToAdmin(securityUser.email)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/custom/token")
    override fun token(@RequestParam("token") token: String): String? {
        return token
    }
}