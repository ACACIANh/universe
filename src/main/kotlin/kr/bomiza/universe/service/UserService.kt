package kr.bomiza.universe.service

import jakarta.transaction.Transactional
import kr.bomiza.universe.common.model.enums.UserRole
import kr.bomiza.universe.domain.user.User
import kr.bomiza.universe.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun getUser(email: String): User? {     //TODO: dto로 교체

        return userRepository.findByEmail(email)
            .orElse(null)
    }

    @Transactional
    fun updateToAdmin(email: String) {
        userRepository.findByEmail(email)
            .ifPresent { user -> user.role = UserRole.ADMIN }
    }
}