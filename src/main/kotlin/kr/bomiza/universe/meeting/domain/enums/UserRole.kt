package kr.bomiza.universe.meeting.domain.enums

enum class UserRole {

    GUEST,
    MEMBER,
    ADMIN,
    ;

    fun getRole(): String {
        return "ROLE_" + this.name
    }
}