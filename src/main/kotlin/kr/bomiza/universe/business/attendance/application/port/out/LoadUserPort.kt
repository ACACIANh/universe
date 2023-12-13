package kr.bomiza.universe.business.attendance.application.port.out

import kr.bomiza.universe.domain.attendance.model.User
import java.util.*

interface LoadUserPort {

    fun loadUser(userId: UUID): User
}