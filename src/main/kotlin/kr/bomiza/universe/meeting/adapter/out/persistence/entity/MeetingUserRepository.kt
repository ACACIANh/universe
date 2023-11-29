package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository

interface MeetingUserRepository : JpaRepository<MeetingUser, Long> {

}