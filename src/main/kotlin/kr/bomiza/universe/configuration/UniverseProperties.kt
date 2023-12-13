package kr.bomiza.universe.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "universe")
data class UniverseProperties(
    val token: Token,
    val meeting: Meeting
) {
    data class Token(
        val cookieName: String,
        val secret: String,
        val expirationMillis: Long,
    )

    data class Meeting(
        val capacity: Int,
    )
}