package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.security.domain.UserState
import java.util.*

class User(
    val id: UUID,
    var state: UserState,
) {
}