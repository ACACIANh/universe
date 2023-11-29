package kr.bomiza.universe.common.model.exception.auth

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import kr.bomiza.universe.common.model.exception.UniverseException
import org.springframework.http.HttpStatus
import java.security.SignatureException

class ValidateTokenException(
    val originException: Throwable
) : UniverseException(
    "Validate token",
    HttpStatus.BAD_REQUEST,
    thisExceptionMessage(originException),
    originException
) {

}

private fun thisExceptionMessage(originException: Throwable): String {
    return when (originException) {
        is SignatureException -> "Invalid JWT signature: ${originException.message}"
        is MalformedJwtException -> "Invalid JWT token: ${originException.message}"
        is ExpiredJwtException -> "JWT token has expired: ${originException.message}"
        is UnsupportedJwtException -> "JWT token is unsupported: ${originException.message}"
        is IllegalArgumentException -> "JWT claims string is empty: ${originException.message}"
        else -> "Unknown exception: ${originException.message}"
    }
}
