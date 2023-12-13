package kr.bomiza.universe.business.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import kr.bomiza.universe.domain.security.model.Authorities
import kr.bomiza.universe.domain.security.enums.ClaimKey
import kr.bomiza.universe.business.security.domain.UserOAuth
import kr.bomiza.universe.business.security.exception.NotExistAuthorizationHeader
import kr.bomiza.universe.business.security.exception.NotExistToken
import kr.bomiza.universe.business.security.exception.ValidateTokenException
import kr.bomiza.universe.configuration.UniverseProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*
import javax.crypto.SecretKey

/**
 * 필수 기능
 * 1. 토큰 발급
 * 2. 토큰 검증
 * 3. 토큰에서 정보 가져오기
 *
 */
@Component
class JwtProvider(

    val universeProperties: UniverseProperties
) {
    private val algorithm = SignatureAlgorithm.HS512

    @Value("\${universe.token.secret}")
    private val jwtSecret: String = ""

    @Value("\${universe.token.expirationMillis}")
    private val jwtExpiration: Long = 0

    // todo: 애플리케이션 뜰때 한번만드는걸로 변경
    private fun signingKey(): SecretKey? {
        val keyBytes = jwtSecret.toByteArray()
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserOAuth   // user 객체 생성
        val expiryDate = Date(Date().time + jwtExpiration)
        val signingKey = signingKey()

        val userAuthorities = userPrincipal.userAuthorities()

        return Jwts.builder()
            .setSubject(userPrincipal.identifier)
            .claim(ClaimKey.EMAIL.name, userPrincipal.email)
            .claim(ClaimKey.AUTHORITIES.name, userAuthorities.joinToString())
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(signingKey, algorithm)
            .compact()
    }

    fun getUserEmail(claims: Claims): String {
        return claims[ClaimKey.EMAIL.name] as String
    }

    fun getUserAuthorities(claims: Claims): Authorities {
        val authorities = claims[ClaimKey.AUTHORITIES.name] as String? ?: return Authorities()
        return Authorities(authorities)
    }

    fun getAuthorizationHeader(request: HttpServletRequest): String {
        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (StringUtils.hasText(authorizationHeader))
            return authorizationHeader

        throw NotExistAuthorizationHeader()
    }

    fun getToken(authorizationHeader: String): String {
        val prefix = "Bearer "
        val token = authorizationHeader.substring(prefix.length)

        if (StringUtils.hasText(token))
            return token

        throw NotExistToken()
    }

    fun validateToken(token: String): Jws<Claims> {
        try {
            val signingKey = signingKey()
            return Jwts
                .parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
        } catch (ex: Exception) {
            throw ValidateTokenException(ex)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JwtProvider::class.java)
    }
}