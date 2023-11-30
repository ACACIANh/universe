package kr.bomiza.universe.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.bomiza.universe.security.domain.SecurityUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

//Component 등록시 자동 filter 등록
class JwtAuthenticationFilter(
    private val excludedUrls: Array<String>,
    private val jwtProvider: JwtProvider,
) : OncePerRequestFilter() {

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {

        return excludedUrls.any { url -> AntPathMatcher().match(url, request.servletPath) }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authorizationHeader = jwtProvider.getAuthorizationHeader(request)

            val token = jwtProvider.getToken(authorizationHeader)

            val validateToken = jwtProvider.validateToken(token)

            val claims = validateToken.body
            val id = UUID.fromString(claims.subject)
            val email = jwtProvider.getUserEmail(claims)
            val userAuthorities = jwtProvider.getUserAuthorities(claims)
            val securityUser = SecurityUser(id, email, userAuthorities)

            val authentication = UsernamePasswordAuthenticationToken(
                securityUser,
                null,
                userAuthorities.toSimpleGrantedAuthorities()
            )

            SecurityContextHolder.getContext().authentication = authentication
        } catch (ex: Exception) {
//            log.error("Failed to Path: ${request.servletPath}")
            log.error("Failed to authenticate user: ${ex.message}")
        } finally {
            filterChain.doFilter(request, response)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    }
}