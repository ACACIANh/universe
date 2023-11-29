package kr.bomiza.universe.common.model.dto.meeting

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import kr.bomiza.universe.domain.meeting.Meeting
import kr.bomiza.universe.domain.user.User
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

    fun toEntity(user: User): Meeting {
        return Meeting(user, date, capacityMember)
    }
}