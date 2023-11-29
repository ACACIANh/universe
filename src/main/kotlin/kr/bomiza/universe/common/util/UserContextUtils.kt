package kr.bomiza.universe.common.util

import kr.bomiza.universe.domain.user.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class UserContextUtils {
    companion object {
        fun getCurrentUser(): User {
            val usernamePasswordAuthenticationToken =
                SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
            val user = usernamePasswordAuthenticationToken.principal as? User
            return user ?: throw IllegalArgumentException("User not found")
        }
    }
}