package kr.bomiza.universe.configuration

import kr.bomiza.universe.common.model.enums.UserRole
import kr.bomiza.universe.security.jwt.JwtAuthenticationEntryPoint
import kr.bomiza.universe.security.jwt.JwtAuthenticationFilter
import kr.bomiza.universe.security.jwt.JwtProvider
import kr.bomiza.universe.security.oauth.OAuth2AuthenticationSuccessHandler
import kr.bomiza.universe.security.oauth.OAuth2UserService
import kr.bomiza.universe.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration(
    val customOAuth2UserService: OAuth2UserService,
    val jwtProvider: JwtProvider,
    val userService: UserService,
    val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    val oAuth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler,
) {
    val excludedUrls: Array<String> =
        arrayOf(
            "/",
            "/favicon.ico",
            "/css/**",
            "/images/**",
            "/js/**",
            "/h2-console/**",
            "/custom/**",
            "/actuator/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
        )

    @Bean
    fun filterChain(http: HttpSecurity, introspector: HandlerMappingIntrospector): SecurityFilterChain {

        http.httpBasic { it.disable() }
            .csrf { it.disable() }
            .headers { it.frameOptions { frame -> frame.disable() } }
            .oauth2Login() { oauth ->
                oauth.userInfoEndpoint { config ->
                    config.userService(customOAuth2UserService)
                }
                oauth.successHandler(oAuth2AuthenticationSuccessHandler)
            }
            .authorizeHttpRequests { auth ->
                excludedUrls.forEach {
                    auth.requestMatchers(AntPathRequestMatcher(it)).permitAll()
                }
                auth.requestMatchers(MvcRequestMatcher.Builder(introspector).pattern(HttpMethod.OPTIONS, "/**"))
                    .permitAll()
                    .requestMatchers(MvcRequestMatcher.Builder(introspector).pattern("/api/v1/**"))
                    .hasAnyRole(UserRole.MEMBER.name, UserRole.ADMIN.name)        // "ROLE_" 자동 삽입
                    .anyRequest()
                    .authenticated()
            }
            .exceptionHandling { exceptionHandle -> exceptionHandle.authenticationEntryPoint(jwtAuthenticationEntryPoint) }
            .logout { logout -> logout.logoutSuccessUrl("/") }
            .sessionManagement { sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(
                JwtAuthenticationFilter(excludedUrls, userService, jwtProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }
}