package kr.bomiza.universe.business.meeting.application.port.out

import kr.bomiza.universe.domain.meeting.model.Meeting
import org.springframework.data.domain.Pageable
import java.util.*

interface LoadMeetingPort {

    // 시간값으로 존재하는지 확인,
    fun findAll(page: Pageable): List<Meeting>
    fun loadMeeting(meetingId: UUID): Meeting
}