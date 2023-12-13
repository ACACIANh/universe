package kr.bomiza.universe.domain.common.exception

open class UniverseException(
    override val message: String,
    val status: Int,
    val exceptionMessage: String?,
    val originThrowable: Throwable?
) : RuntimeException() {
}