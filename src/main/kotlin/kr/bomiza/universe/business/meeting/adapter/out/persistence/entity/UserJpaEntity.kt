package kr.bomiza.universe.business.meeting.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kr.bomiza.universe.common.entity.UserEntity
import kr.bomiza.universe.common.enums.UserState
import kr.bomiza.universe.common.enums.UserRole
import java.util.*

@Entity(name = "UserJpaEntityMeeting")
@Table(name = "`user`")
class UserJpaEntity(
    id: UUID,
//    val email: String,
    var name: String,

    @Enumerated(EnumType.STRING)
    var state: UserState,

    @Enumerated(EnumType.STRING)
    var role: UserRole,

    ) : UserEntity(id) {

}