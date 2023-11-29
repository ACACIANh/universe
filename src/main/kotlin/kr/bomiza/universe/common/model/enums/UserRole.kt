package kr.bomiza.universe.common.model.enums

enum class UserRole {

    GUEST,
    MEMBER,
    ADMIN,
    ;

    fun getRole(): String {
        return "ROLE_" + this.name
    }
}