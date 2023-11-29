package kr.bomiza.universe.meeting.domain.model

import kr.bomiza.universe.meeting.domain.enums.UserState
import java.util.UUID

class User(
    val id: UUID,
    var state: UserState,
) {
}