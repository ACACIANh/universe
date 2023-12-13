package kr.bomiza.universe.business.attendance.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kr.bomiza.universe.common.entity.UserEntity
import kr.bomiza.universe.domain.common.enums.UserState
import java.util.*

@Entity
@Table(name = "`user`")
class UserJpaEntity(
    id: UUID,
//    val email: String,
    var name: String,

    @Enumerated(EnumType.STRING)
    var state: UserState,

    ) : UserEntity(id) {

}