package kr.bomiza.universe.common.model.dto

data class ExceptionResponse(
    val message: String,
    val exceptionType: String,
    val exceptionMessage: String?
) {
    constructor(originException: Exception) :
            this(
                originException.message ?: "Internal Server Error occurred",
                originException::class.java.simpleName, originException.message
            )
}