package kr.bomiza.universe.meeting.adapter.`in`.web

import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.MeetingResponseDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.MeetingUsersResponseDto
import kr.bomiza.universe.meeting.domain.model.Meeting
import kr.bomiza.universe.meeting.domain.model.MeetingUser
import org.springframework.stereotype.Component

@Component
class MeetingMessageMapper {

    fun mapToDto(meeting: Meeting): MeetingResponseDto {

        return MeetingResponseDto(
            meeting.id,
            meeting.date,
            meeting.capacityMember,
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