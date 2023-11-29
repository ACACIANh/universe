package kr.bomiza.universe.common.model.exception

import org.springframework.http.HttpStatus

open class UniverseException(
    override val message: String,
    val status: HttpStatus,
    val exceptionMessage: String?,
    val originThrowable: Throwable?
) : RuntimeException() {
}