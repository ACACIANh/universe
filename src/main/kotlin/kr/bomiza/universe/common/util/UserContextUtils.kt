package kr.bomiza.universe.common.util

import kr.bomiza.universe.security.domain.SecurityUser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class UserContextUtils {
    companion object {
        fun getCurrentUser(): SecurityUser {
            val usernamePasswordAuthenticationToken =
                SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
            val user = usernamePasswordAuthenticationToken.principal as? SecurityUser
            return user ?: throw IllegalArgumentException("User not found")
        }

        fun setSecurityUser(securityUser: SecurityUser) {
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                securityUser, null, securityUser.authorities.toSimpleGrantedAuthorities()
            )
        }
    }
}