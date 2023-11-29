package kr.bomiza.universe.common

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory

abstract class SelfValidating<T> {
    private val validator: Validator

    init {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    protected fun validateSelf() {
        val violations: Set<ConstraintViolation<T>> = validator.validate(this as T)
        if (!violations.isEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}

