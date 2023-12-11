package kr.bomiza.universe.meeting.application.port.`in`

import kr.bomiza.universe.meeting.domain.model.Meeting
import org.springframework.data.domain.Pageable

interface FindMeetingUseCase {

    fun findAll(page: Pageable): Collection<Meeting>
}