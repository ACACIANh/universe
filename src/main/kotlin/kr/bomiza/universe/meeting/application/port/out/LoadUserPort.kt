package kr.bomiza.universe.meeting.application.port.out

import kr.bomiza.universe.meeting.domain.model.User
import java.util.*

interface LoadUserPort {

    fun loadUser(userId: UUID): User
}