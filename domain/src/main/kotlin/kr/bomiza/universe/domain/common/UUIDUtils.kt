package kr.bomiza.universe.domain.common

import java.util.*

class UUIDUtils {
    companion object {
        fun generate(): UUID {
            return UUID.randomUUID()
        }
    }
}