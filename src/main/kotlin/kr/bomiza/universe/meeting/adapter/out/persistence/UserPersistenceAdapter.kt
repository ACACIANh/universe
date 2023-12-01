package kr.bomiza.universe.meeting.adapter.out.persistence

import kr.bomiza.universe.common.annotation.PersistenceAdapter
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.UserRepository
import kr.bomiza.universe.meeting.application.port.out.LoadUserPort
import kr.bomiza.universe.meeting.domain.exception.NotFoundUserException
import kr.bomiza.universe.meeting.domain.model.User
import java.util.*

@PersistenceAdapter
class UserPersistenceAdapter(
    val userRepository: UserRepository,
    val meetingPersistenceMapper: MeetingPersistenceMapper
) : LoadUserPort {

    //todo: 검증로직이 여기있는게 맞는지 검토
    override fun loadUser(userId: UUID): User {
        val userEntity = userRepository.findById(userId)
            .orElseThrow { NotFoundUserException(userId.toString()) }
        return meetingPersistenceMapper.mapToDomain(userEntity)
    }
}