package kr.bomiza.universe.domain.meeting.model

import kr.bomiza.universe.common.enums.UserState
import kr.bomiza.universe.common.model.Base
import kr.bomiza.universe.common.enums.UserRole
import java.util.*

class User(
    id: UUID,
    val name: String,
    var state: UserState,
    var role: UserRole,
) : Base(id) {
    constructor(name: String, state: UserState, role: UserRole) : this(newInstance(), name, state, role)
}