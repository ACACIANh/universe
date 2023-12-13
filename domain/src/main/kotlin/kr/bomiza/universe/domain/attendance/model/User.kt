package kr.bomiza.universe.domain.attendance.model

import kr.bomiza.universe.common.enums.UserState
import kr.bomiza.universe.common.model.Base
import java.util.*

class User(
    id: UUID,
    val name: String,
    var state: UserState,
) : Base(id) {
    constructor(name: String, state: UserState) : this(newInstance(), name, state)
}