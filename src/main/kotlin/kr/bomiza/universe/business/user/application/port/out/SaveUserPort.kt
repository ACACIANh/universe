package kr.bomiza.universe.business.user.application.port.out

import kr.bomiza.universe.domain.user.model.User

interface SaveUserPort {

    fun saveUser(user: User)
}