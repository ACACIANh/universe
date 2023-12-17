package kr.bomiza.universe.business.meeting.adapter.out.persistence.entity

import java.time.LocalDate
import java.util.UUID

interface MeetingUserSupport {

    fun findByUserIdAndDate(userId: UUID, date: LocalDate): List<MeetingUserJpaEntity>
}