package kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "정모생성 요청")
class MeetingCreateRequestDto(

    @get:JsonProperty("date")
    @param:JsonProperty("date")
    @Schema(description = "정모일자")
    val date: LocalDate,

    @get:JsonProperty("capacityMember")
    @param:JsonProperty("capacityMember")
    @Schema(description = "정모인원", example = "16")
    val capacityMember: Int,
) {
}