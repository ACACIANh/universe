package kr.bomiza.universe.common.web

import kr.bomiza.universe.common.ExceptionResponse
import kr.bomiza.universe.common.UniverseException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    // application custom exception
    @ExceptionHandler(UniverseException::class)
    fun handleInternalServer(e: UniverseException): ResponseEntity<ExceptionResponse> {

        log.error("Internal Server Error occurred: ${e.message}", e)

        return ResponseEntity.status(e.status)
            .body<ExceptionResponse>(
                ExceptionResponse(
                    message = e.message,
                    exceptionType = e::class.java.simpleName,
                    exceptionMessage = e.exceptionMessage
                )
            )
    }

    // validation exception
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validateBadRequest(e: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {

        val errors = e.bindingResult.fieldErrors
            .groupBy(FieldError::getDefaultMessage)
            .mapValues { it.value.map(FieldError::getField) }
        val message = errors.toString()
        log.warn(message, e)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body<ExceptionResponse>(
                ExceptionResponse(
                    message = message,
                    exceptionType = e::class.java.simpleName,
                    exceptionMessage = e.message
                )
            )
    }

    // common exception
    @ExceptionHandler(Exception::class)
    fun handleInternalServer(e: Exception): ResponseEntity<ExceptionResponse> {

        log.error("Internal Server Error occurred: ${e.message}", e)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body<ExceptionResponse>(
                ExceptionResponse(
                    message = "Internal Server Error occurred",
                    exceptionType = e::class.java.simpleName,
                    exceptionMessage = e.message
                )
            )
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ExceptionControllerAdvice::class.java)
    }
}