package kr.bomiza.universe.meeting.application.port.`in`

import kr.bomiza.universe.domain.meeting.model.Meeting
import org.springframework.data.domain.Pageable

interface FindMeetingUseCase {

    fun findAll(page: Pageable): Collection<Meeting>
}