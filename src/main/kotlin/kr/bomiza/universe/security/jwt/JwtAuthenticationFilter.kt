package kr.bomiza.universe.security.jwt

import kr.bomiza.universe.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

//Component 등록시 자동 filter 등록
class JwtAuthenticationFilter(

    val excludedUrls: Array<String>,
    val userService: UserService,
    val jwtProvider: JwtProvider,
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
            val email = claims.subject
            val user = userService.getUser(email)
            val authorities = mutableListOf(user?.role?.name)

            val authentication = UsernamePasswordAuthenticationToken(
                user,
                null,
                authorities.map { SimpleGrantedAuthority(user?.role?.getRole()) } // todo: 람다? + getRole 필요성 체크
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