package kr.bomiza.universe.common.entity

import jakarta.persistence.MappedSuperclass
import java.util.*

@MappedSuperclass
abstract class UserEntity(id: UUID) : BaseEntity(id) {
}