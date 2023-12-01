package kr.bomiza.universe.meeting.application.port.`in`

import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.meeting.domain.model.Meeting
import java.util.*

interface CreateMeetingUseCase {

    fun createMeeting(userId: UUID, requestDto: MeetingCreateRequestDto) : Meeting
}