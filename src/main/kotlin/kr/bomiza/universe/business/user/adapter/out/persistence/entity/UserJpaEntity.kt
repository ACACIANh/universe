package kr.bomiza.universe.business.user.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kr.bomiza.universe.common.entity.UserEntity
import kr.bomiza.universe.common.enums.UserRole
import kr.bomiza.universe.common.enums.UserState
import java.util.*

@Entity(name = "UserJpaEntityUser")
@Table(name = "`user`")
class UserJpaEntity(
    id: UUID,

    var name: String,

    @Enumerated(EnumType.STRING)
    var state: UserState,

    @Enumerated(EnumType.STRING)
    var role: UserRole,

    ) : UserEntity(id) {

}