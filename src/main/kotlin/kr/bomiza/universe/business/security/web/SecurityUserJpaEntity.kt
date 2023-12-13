package kr.bomiza.universe.business.security.web

import jakarta.persistence.*
import kr.bomiza.universe.business.security.domain.Authority
import kr.bomiza.universe.business.security.domain.OAuthUserContext
import kr.bomiza.universe.common.entity.UserEntity
import kr.bomiza.universe.domain.common.enums.UserState
import kr.bomiza.universe.domain.meeting.enums.UserRole
import java.util.*

@Entity
@Table(name = "`user`")
class SecurityUserJpaEntity(
    id: UUID,
    val email: String,

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "authorities", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "authority")
    val authorities: Set<Authority>,

    @Enumerated(EnumType.STRING)
    private var state: UserState,

    @Enumerated(EnumType.STRING)
    private var role: UserRole,

    private var name: String,

    private var picture: String,
) : UserEntity(id) {

    fun update(userContext: OAuthUserContext): SecurityUserJpaEntity {
        this.name = userContext.name
        this.picture = userContext.picture
        return this
    }

    fun addAuthority(authority: Authority): SecurityUserJpaEntity {
        authorities.plusElement(authority)
        return this
    }
}