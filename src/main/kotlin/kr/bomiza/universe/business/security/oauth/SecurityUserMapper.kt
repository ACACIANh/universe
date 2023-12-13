package kr.bomiza.universe.business.security.oauth

import kr.bomiza.universe.domain.security.model.Authorities
import kr.bomiza.universe.domain.security.model.OAuthUserContext
import kr.bomiza.universe.domain.security.model.SecurityUser
import kr.bomiza.universe.business.security.web.SecurityUserJpaEntity
import kr.bomiza.universe.domain.common.enums.UserState
import kr.bomiza.universe.domain.meeting.enums.UserRole

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