package kr.bomiza.universe.business.meeting.application.port.`in`

import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.domain.meeting.model.Meeting
import java.util.*

interface CreateMeetingUseCase {

    fun createMeeting(userId: UUID, requestDto: MeetingCreateRequestDto) : Meeting
}