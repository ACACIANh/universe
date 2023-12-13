package kr.bomiza.universe.domain.common.util

import java.util.*

class UUIDUtils {
    companion object {
        fun generate(): UUID {
            return UUID.randomUUID()
        }
    }
}