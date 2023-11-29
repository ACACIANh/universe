package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kr.bomiza.universe.common.BaseEntity
import kr.bomiza.universe.common.util.UUIDUtils
import kr.bomiza.universe.meeting.domain.enums.UserRole
import kr.bomiza.universe.meeting.domain.enums.UserState

@Entity
@Table(name = "`user`")
class UserJpaEntity(
    var name: String,
    val email: String,
    var picture: String,
    @Enumerated(EnumType.STRING)
    var state: UserState,
    @Enumerated(EnumType.STRING)
    var role: UserRole,            //혹시 list 로 교체 가능성
    // role 과 authorities 구분 하기~

    // TODO: provider 토큰 추가

) : BaseEntity(UUIDUtils.generate()) {

    fun update(name: String, picture: String): UserJpaEntity {
        this.name = name
        this.picture = picture

        return this
    }

    fun getRoleKey(): String {
        return "ROLE_" + role.name
    }
}