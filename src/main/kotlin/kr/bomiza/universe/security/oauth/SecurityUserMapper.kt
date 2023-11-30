package kr.bomiza.universe.security.oauth

import kr.bomiza.universe.security.domain.Authorities
import kr.bomiza.universe.security.domain.OAuthUserContext
import kr.bomiza.universe.security.domain.SecurityUser
import kr.bomiza.universe.security.web.SecurityUserJpaEntity

class SecurityUserMapper {

    companion object {
        fun toEntity(securityUser: SecurityUser, userContext: OAuthUserContext): SecurityUserJpaEntity {
            return SecurityUserJpaEntity(
                securityUser.id,
                securityUser.email,
                securityUser.authorities.authorities,
                userContext.name,
                userContext.picture
            )
        }

        fun toDomain(securityUserJpaEntity: SecurityUserJpaEntity): SecurityUser {
            return SecurityUser(
                securityUserJpaEntity.id,
                securityUserJpaEntity.email,
                Authorities(securityUserJpaEntity.authorities)
            )
        }
    }
}