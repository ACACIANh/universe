package kr.bomiza.universe.business.attendance.adapter.`in`.web.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

//JsonCreator, JsonProperty 대신 -> @get: @param: 사용
@Schema(description = "출석체크 요청")
class AttendanceRequestDto constructor(
    @get:JsonProperty("checkIn")
    @param:JsonProperty("checkIn")
    @Schema(example = "true", description = "입장시 true 퇴장시 false")
    val checkIn: Boolean
) {
}