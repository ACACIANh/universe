package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository

interface MeetingRepository : JpaRepository<Meeting, Long> {
}