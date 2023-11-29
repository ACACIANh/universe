package kr.bomiza.universe.domain.meeting

import org.springframework.data.jpa.repository.JpaRepository

interface MeetingUserRepository : JpaRepository<MeetingUser, Long> {

}