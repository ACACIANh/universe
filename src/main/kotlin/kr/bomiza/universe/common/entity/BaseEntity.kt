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
    val id: UUID
) {
    @CreatedDate
    var createdDate: LocalDateTime? = null

    @LastModifiedDate
    var modifiedDate: LocalDateTime? = null
}