package kr.bomiza.universe.business.meeting.application.port.`in`

import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinUpdateRequestDto
import kr.bomiza.universe.domain.meeting.model.MeetingUser
import java.util.*

interface JoinMeetingUseCase {

    fun joinMeeting(userId: UUID, meetingId: UUID, requestDto: MeetingJoinRequestDto): MeetingUser

    fun joinMeetingUpdate(userId: UUID, meetingUserId: UUID, requestDto: MeetingJoinUpdateRequestDto): MeetingUser
}