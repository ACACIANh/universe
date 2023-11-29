package kr.bomiza.universe.common.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {
    override fun serialize(
        value: LocalDateTime?,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        val formattedDateTime = value?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
//        val formattedDateTime = value?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        gen.writeString(formattedDateTime)
    }
}