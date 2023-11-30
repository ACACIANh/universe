package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.meeting.domain.enums.UserRole
import kr.bomiza.universe.security.domain.UserState
import java.util.*

class User(
    val id: UUID,
    val name: String,
    var state: UserState,
    var role: UserRole,
) {
}