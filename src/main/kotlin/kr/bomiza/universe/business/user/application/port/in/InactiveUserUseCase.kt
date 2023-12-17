package kr.bomiza.universe.business.user.application.port.`in`

import java.util.UUID

interface InactiveUserUseCase {

    fun inactiveUser(userId: UUID)
}