package kr.bomiza.universe.security.web

import jakarta.transaction.Transactional
import kr.bomiza.universe.security.domain.Authority
import org.springframework.stereotype.Service

@Service
class SecurityUserService(
    val securityUserRepository: SecurityUserRepository
) {
    @Transactional
    fun updateToAdmin(email: String) {
        securityUserRepository.findByEmail(email)
            .ifPresent { user -> user.addAuthority(Authority.ADMIN) }
    }
}