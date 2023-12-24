package kr.bomiza.universe.common.event

import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.bomiza.universe.common.entity.BaseEntity
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "event")
class EventEntity(

    val eventName: String,

    val timeStamp: LocalDateTime

) : BaseEntity(UUID.randomUUID()) {
}