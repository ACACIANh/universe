package kr.bomiza.universe.common.util

import kr.bomiza.universe.domain.security.model.SecurityUser
import kr.bomiza.universe.business.security.domain.toSimpleGrantedAuthorities
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