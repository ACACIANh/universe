package kr.bomiza.universe.common.model.dto.meeting

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import kr.bomiza.universe.domain.meeting.Meeting
import java.time.LocalDate

@Schema(description = "정모 응답")
class MeetingResponseDto(

    @get:JsonProperty("id")
    @param:JsonProperty("id")
    @Schema(description = "정모 id")
    var id: Long,

    @get:JsonProperty("date")
    @param:JsonProperty("date")
    @Schema(description = "정모 일자")
    var date: LocalDate,

    @get:JsonProperty("capacityMember")
    @param:JsonProperty("capacityMember")
    @Schema(description = "정모 인원")
    var capacityMember: Int,

    @get:JsonProperty("meetingUsers")
    @param:JsonProperty("meetingUsers")
    @Schema(description = "정모 참여 리스트")
    var meetingUsers: List<MeetingUsersResponseDto>,
) {
    constructor(entity: Meeting) : this(
        entity.id!!,
        entity.date,
        entity.capacityMember,
        entity.meetingUsers.map(::MeetingUsersResponseDto)
    )
}