package kr.bomiza.universe.security.oauth

import kr.bomiza.universe.configuration.UniverseProperties
import kr.bomiza.universe.security.jwt.JwtProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Component
class OAuth2AuthenticationSuccessHandler(

    val jwtProvider: JwtProvider,
    val universeProperties: UniverseProperties,
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val generateToken = jwtProvider.generateToken(authentication!!)

        val targetUri = UriComponentsBuilder.fromUriString("/custom/token")
            .queryParam("token", generateToken)
            .build().toUriString()

        redirectStrategy.sendRedirect(request, response, targetUri)

        log.info(generateToken)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(OAuth2AuthenticationSuccessHandler::class.java)
    }
}