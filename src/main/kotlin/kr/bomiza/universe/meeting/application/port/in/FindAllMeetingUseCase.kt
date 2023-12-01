package kr.bomiza.universe.meeting.application.port.`in`

import kr.bomiza.universe.meeting.domain.model.Meeting

interface FindAllMeetingUseCase {

    fun findAll(): Collection<Meeting>
}