package kr.bomiza.universe.security.oauth

import kr.bomiza.universe.domain.meeting.enums.UserRole
import kr.bomiza.universe.security.domain.Authorities
import kr.bomiza.universe.security.domain.OAuthUserContext
import kr.bomiza.universe.security.domain.SecurityUser
import kr.bomiza.universe.domain.common.UserState
import kr.bomiza.universe.security.web.SecurityUserJpaEntity

class SecurityUserMapper {

    companion object {
        fun toEntity(securityUser: SecurityUser, userContext: OAuthUserContext): SecurityUserJpaEntity {
            return SecurityUserJpaEntity(
                securityUser.id,
                securityUser.email,
                securityUser.authorities.authorities,
                //todo: 모델 분리하는게 맞는지에 대한 검토, 그거와 별개로 파라미터로 넣는 고정값 수정
                state = UserState.ACTIVATE,
                role = UserRole.MEMBER,
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