package kr.bomiza.universe.domain.meeting

import org.springframework.data.jpa.repository.JpaRepository

interface MeetingRepository : JpaRepository<Meeting, Long> {
}