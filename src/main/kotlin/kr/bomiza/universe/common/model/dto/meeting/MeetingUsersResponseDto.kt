package kr.bomiza.universe.common.model.dto.meeting

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import kr.bomiza.universe.common.model.enums.MeetingUserState
import kr.bomiza.universe.domain.meeting.MeetingUser
import java.time.LocalTime

@Schema(description = "정모참여상태 응답")
class MeetingUsersResponseDto(

    @get:JsonProperty("meetingUserId")
    @param:JsonProperty("meetingUserId")
    @Schema(type = "String", description = "정모 참여 id")
    val meetingUserId: Long,

    @get:JsonProperty("userId")
    @param:JsonProperty("userId")
    @Schema(description = "정모 참여자 id")
    val userId: Long,       // 이 필드를 simple user 이런식으로 만들까?

    @get:JsonProperty("userName")
    @param:JsonProperty("userName")
    @Schema(description = "정모 참여자 이름")
    val userName: String,

    @get:JsonProperty("state")
    @param:JsonProperty("state")
    @Schema(description = "정모참여 상태")
    val state: MeetingUserState,

    @get:JsonProperty("joinTime")
    @param:JsonProperty("joinTime")
    @Schema(type = "String", pattern = "HH:mm:SS", example = "15:30:00", description = "입장 예상 시간")
    val joinTime: LocalTime,

    @get:JsonProperty("isGuest")
    @param:JsonProperty("isGuest")
    @Schema(description = "게스트시 true", example = "false")
    val isGuest: Boolean,
) {
    constructor(entity: MeetingUser) : this(
        meetingUserId = entity.id!!,
        userId = entity.user.id!!,
        userName = entity.user.name,
        state = entity.state,
        joinTime = entity.joinTime,
        isGuest = entity.guest
    )
}