package kr.bomiza.universe.business.user.adapter.`in`.web

import kr.bomiza.universe.business.user.adapter.`in`.web.model.response.UserResponseDto
import kr.bomiza.universe.business.user.application.port.`in`.FindUserUseCase
import kr.bomiza.universe.business.user.application.port.`in`.InactiveUserUseCase
import kr.bomiza.universe.common.annotation.WebAdapter
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@WebAdapter
@RestController
class UserController(
    val userMessageMapper: UserMessageMapper,
    val findUserUseCase: FindUserUseCase,
    val inactiveUserUseCase: InactiveUserUseCase,
) : IUserController {

    @GetMapping("/api/admin/v1/users")
    override fun findAllAttendance(page: Pageable): ResponseEntity<List<UserResponseDto>> {
        val users = findUserUseCase.findAllUser(page)
        val responseDto = userMessageMapper.mapToDto(users)
        return ResponseEntity.ok(responseDto)
    }

    @PostMapping("/api/admin/v1/users/{userId}")
    override fun inactiveUser(
        @PathVariable userId: UUID,
    ): ResponseEntity<UserResponseDto> {

        inactiveUserUseCase.inactiveUser(userId)
        return ResponseEntity.ok().build()
    }
}