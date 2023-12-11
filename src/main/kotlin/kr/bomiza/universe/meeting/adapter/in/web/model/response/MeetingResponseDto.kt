package kr.bomiza.universe.meeting.adapter.`in`.web.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.util.*

@Schema(description = "정모 응답")
class MeetingResponseDto(

    @get:JsonProperty("id")
    @param:JsonProperty("id")
    @Schema(description = "정모 id")
    val id: UUID,

    @get:JsonProperty("date")
    @param:JsonProperty("date")
    @Schema(description = "정모 일자")
    val date: LocalDate,

    @get:JsonProperty("capacityMember")
    @param:JsonProperty("capacityMember")
    @Schema(description = "정모 인원")
    val capacityMember: Int,

    @get:JsonProperty("meetingUsers")
    @param:JsonProperty("meetingUsers")
    @Schema(description = "정모 참여 리스트")
    val meetingUsers: List<MeetingUsersResponseDto>,
) {
}