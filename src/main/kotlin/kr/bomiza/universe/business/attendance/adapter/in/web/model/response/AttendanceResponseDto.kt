package kr.bomiza.universe.business.attendance.adapter.`in`.web.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@Schema(description = "출석체크 응답")
class AttendanceResponseDto(
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    @Schema(description = "정모 id")
    var id: UUID,

    @get:JsonProperty("userId")
    @param:JsonProperty("userId")
    @Schema(description = "정모 참여자 id")
    var userId: UUID,

    @get:JsonProperty("checkIn")
    @param:JsonProperty("checkIn")
    @Schema(description = "입장 시간")
    var checkIn: LocalDateTime?,

    @get:JsonProperty("checkOut")
    @param:JsonProperty("checkOut")
    @Schema(description = "퇴장 시간")
    var checkOut: LocalDateTime?
) {

}