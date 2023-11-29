package kr.bomiza.universe.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "universe")
data class UniverseProperties(
    val token: Token,
) {
    data class Token(
        val cookieName: String,
        val secret: String,
        val expirationMillis: Long,
    )
}