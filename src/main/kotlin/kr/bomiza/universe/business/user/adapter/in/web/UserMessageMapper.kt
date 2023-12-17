package kr.bomiza.universe.business.user.adapter.`in`.web

import kr.bomiza.universe.business.user.adapter.`in`.web.model.response.UserResponseDto
import kr.bomiza.universe.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class UserMessageMapper {

    fun mapToDto(user: User): UserResponseDto {
        return UserResponseDto(user.id, user.name, user.state, user.role)
    }

    fun mapToDto(users: List<User>): List<UserResponseDto> {
        return users.map { mapToDto(it) }
    }
}