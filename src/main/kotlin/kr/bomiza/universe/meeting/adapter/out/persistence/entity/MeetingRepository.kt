package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeetingRepository : JpaRepository<MeetingJpaEntity, UUID> {
}