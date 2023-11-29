package kr.bomiza.universe.common.jackson

import com.fasterxml.jackson.databind.module.SimpleModule
import java.time.LocalDateTime

class CustomObjectMapperModule : SimpleModule() {
    init {
        addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
    }
}