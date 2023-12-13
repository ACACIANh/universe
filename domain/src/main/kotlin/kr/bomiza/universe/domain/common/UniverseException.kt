package kr.bomiza.universe.domain.common

open class UniverseException(
    override val message: String,
    val status: Int,
    val exceptionMessage: String?,
    val originThrowable: Throwable?
) : RuntimeException() {
}