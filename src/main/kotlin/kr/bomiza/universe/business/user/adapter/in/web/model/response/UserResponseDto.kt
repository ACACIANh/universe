package kr.bomiza.universe.business.user.adapter.`in`.web.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import kr.bomiza.universe.common.enums.UserRole
import kr.bomiza.universe.common.enums.UserState
import java.util.*

@Schema(description = "회원 응답")
class UserResponseDto(

    @get:JsonProperty("id")
    @param:JsonProperty("id")
    @Schema(description = "회원 id")
    val id: UUID,

    @get:JsonProperty("name")
    @param:JsonProperty("name")
    @Schema(description = "회원 이름")
    val name: String,

    @get:JsonProperty("state")
    @param:JsonProperty("state")
    @Schema(description = "회원 상태")
    var state: UserState,

    @get:JsonProperty("role")
    @param:JsonProperty("role")
    @Schema(description = "회원 역할")
    var role: UserRole,
) {
}