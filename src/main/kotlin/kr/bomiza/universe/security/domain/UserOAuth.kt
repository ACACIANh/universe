package kr.bomiza.universe.security.domain

import org.springframework.security.oauth2.core.user.OAuth2User
import java.io.Serializable

class UserOAuth(
    private val defaultOAuth2User: OAuth2User,
    private val user: SecurityUser      // todo: 추후 UserOAuth 자리에 SecurityUser 그 자체가 될수 있게 변경하기,,
) : OAuth2User by defaultOAuth2User, Serializable {

    val identifier: String
        get() = user.id.toString()

    val email: String
        get() = user.email

    fun userAuthorities(): Authorities {
        return user.authorities
    }
}