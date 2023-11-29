package kr.bomiza.universe.domain.user

import kr.bomiza.universe.common.model.enums.UserRole
import kr.bomiza.universe.common.model.enums.UserState
import kr.bomiza.universe.domain.BaseEntity
import jakarta.persistence.*

@Entity
class User(
    var name: String,
    val email: String,
    var picture: String,
    @Enumerated(EnumType.STRING)
    var state: UserState,
    @Enumerated(EnumType.STRING)
    var role: UserRole,            //혹시 list 로 교체 가능성
    // role 과 authorities 구분 하기~
    
    // TODO: provider 토큰 추가

) : BaseEntity() {

    fun update(name: String, picture: String): User {
        this.name = name
        this.picture = picture

        return this
    }

    fun getRoleKey(): String {
        return "ROLE_" + role.name
    }
}