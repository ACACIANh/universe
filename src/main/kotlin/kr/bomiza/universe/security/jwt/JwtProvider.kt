package kr.bomiza.universe.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import kr.bomiza.universe.security.oauth.UserOAuth
import kr.bomiza.universe.configuration.UniverseProperties
import kr.bomiza.universe.security.exception.NotExistAuthorizationHeader
import kr.bomiza.universe.security.exception.NotExistToken
import kr.bomiza.universe.security.exception.ValidateTokenException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*

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

    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserOAuth   // user 객체 생성

        val expiryDate = Date(Date().time + jwtExpiration)

        // todo: 추후 중복제거 세번반복
        val keyBytes = jwtSecret.toByteArray()
        val signingKey = Keys.hmacShaKeyFor(keyBytes)

        return Jwts.builder()
            .setSubject(userPrincipal.identifier)      // 고유값
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(signingKey, algorithm)
            .compact()
    }

    fun getUserEmailFromToken(token: String): String {

        val keyBytes = jwtSecret.toByteArray()
        val signingKey = Keys.hmacShaKeyFor(keyBytes)

        val claims: Claims = Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body

        return claims.subject
    }

    fun getAuthorizationHeader(request: HttpServletRequest): String {
        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (StringUtils.hasText(authorizationHeader))
            return authorizationHeader

        throw NotExistAuthorizationHeader()
    }

    fun getToken(authorizationHeader: String): String {
        val token = authorizationHeader.substring(7)

        if (StringUtils.hasText(token))
            return token

        throw NotExistToken()
    }

    fun validateToken(token: String): Jws<Claims> {
        try {
            val keyBytes = jwtSecret.toByteArray()
            val signingKey = Keys.hmacShaKeyFor(keyBytes)
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