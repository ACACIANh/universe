package kr.bomiza.universe.common

import kr.bomiza.universe.common.exception.UniverseException

data class ExceptionResponse(
    val message: String,
    val exceptionType: String,
    val exceptionMessage: String?
) {
    constructor(originException: Exception) : this(
        "Internal Server Error occurred",
        originException::class.java.simpleName,
        originException.message
    )

    constructor(originException: UniverseException) : this(
        originException.message,
        originException::class.java.simpleName,
        originException.exceptionMessage
    )
}