package kr.bomiza.universe.business.meeting.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeetingUserRepository : JpaRepository<MeetingUserJpaEntity, UUID> {

}