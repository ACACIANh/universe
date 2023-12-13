package kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalTime

@Schema(description = "정모 참석 요청")
class MeetingJoinRequestDto(

    @get:JsonProperty("isGuest")
    @param:JsonProperty("isGuest")
    @Schema(description = "게스트시 true", example = "false")
    val isGuest: Boolean,

    @get:JsonProperty("joinTime")
    @param:JsonProperty("joinTime")
    @Schema(type = "String", pattern = "HH:mm:SS", example = "15:30:00", description = "입장 예상 시간")
    val joinTime: LocalTime,
) {
}