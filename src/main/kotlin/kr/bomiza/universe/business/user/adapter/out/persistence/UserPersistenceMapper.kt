package kr.bomiza.universe.business.user.adapter.out.persistence

import kr.bomiza.universe.business.user.adapter.out.persistence.entity.UserJpaEntity
import kr.bomiza.universe.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceMapper {

    fun mapToDomain(entity: UserJpaEntity): User {
        return User(entity.id, entity.name, entity.state, entity.role)
    }

    fun mapToEntity(user: User): UserJpaEntity {
        return UserJpaEntity(user.id, user.name, user.state, user.role)
    }
}