package kr.bomiza.universe.meeting.application.legacy

import jakarta.transaction.Transactional
import kr.bomiza.universe.meeting.domain.enums.UserRole
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.User
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.UserRepository
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