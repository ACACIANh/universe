package kr.bomiza.universe.common.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @Id
    open val id: UUID
) {

    @CreatedDate
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    lateinit var modifiedDate: LocalDateTime
}