package kr.bomiza.universe.web.controller

import kr.bomiza.universe.service.UserService
import kr.bomiza.universe.common.util.UserContext
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController(
    val userService: UserService
) : IAdminController {

    @PostMapping("/api/v1/member/admin")
    override fun userUpdateAdmin(): ResponseEntity<Unit> {

        val user = UserContext.getCurrentUser()

        userService.updateToAdmin(user.email)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/custom/token")
    override fun token(@RequestParam("token") token: String): String? {
        return token
    }
}