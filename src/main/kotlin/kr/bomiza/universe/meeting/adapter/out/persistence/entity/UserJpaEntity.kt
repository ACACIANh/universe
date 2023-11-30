package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kr.bomiza.universe.common.entity.UserEntity
import kr.bomiza.universe.meeting.domain.enums.UserRole
import kr.bomiza.universe.security.domain.UserState
import java.util.*

@Entity
@Table(name = "`user`")
class UserJpaEntity(
    val email: String,
    var name: String,
    @Enumerated(EnumType.STRING)
    var state: UserState,
    @Enumerated(EnumType.STRING)
    var role: UserRole,
) : UserEntity(UUID.randomUUID()) {

}