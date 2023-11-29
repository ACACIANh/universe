package kr.bomiza.universe.common.util

import kr.bomiza.universe.meeting.adapter.out.persistence.entity.UserJpaEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class UserContextUtils {
    companion object {
        fun getCurrentUser(): UserJpaEntity {
            val usernamePasswordAuthenticationToken =
                SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
            val user = usernamePasswordAuthenticationToken.principal as? UserJpaEntity
            return user ?: throw IllegalArgumentException("User not found")
        }
    }
}