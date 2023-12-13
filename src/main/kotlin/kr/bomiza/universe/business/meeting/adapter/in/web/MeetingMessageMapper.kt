package kr.bomiza.universe.business.meeting.adapter.`in`.web

import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.response.MeetingResponseDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.response.MeetingUsersResponseDto
import kr.bomiza.universe.domain.meeting.model.Meeting
import kr.bomiza.universe.domain.meeting.model.MeetingUser
import org.springframework.stereotype.Component

@Component
class MeetingMessageMapper {

    fun mapToDto(meeting: Meeting): MeetingResponseDto {

        return MeetingResponseDto(
            meeting.id,
            meeting.date,
            meeting.meetingUsers.capacity,
            meeting.meetingUsers.meetingUsers.map { mapToDto(it) })
    }

    fun mapToDto(meetingUser: MeetingUser): MeetingUsersResponseDto {

        return MeetingUsersResponseDto(
            meetingUser.id,
            meetingUser.user.id,
            meetingUser.user.name,
            meetingUser.state,
            meetingUser.joinTime,
            meetingUser.guest
        )
    }
}