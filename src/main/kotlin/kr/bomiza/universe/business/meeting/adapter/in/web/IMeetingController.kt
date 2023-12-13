package kr.bomiza.universe.business.meeting.adapter.`in`.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinUpdateRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.response.MeetingResponseDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.response.MeetingUsersResponseDto
import kr.bomiza.universe.business.security.domain.SecurityUser
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Tag(name = "Meeting", description = "정모")
@SecurityRequirement(name = "Authorization")
interface IMeetingController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "정모 생성", description = "정모생성 설명")
    fun createMeeting(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestBody requestDto: MeetingCreateRequestDto
    ): ResponseEntity<Long>

    @Operation(summary = "정모 참여", description = "정모 참여 설명")
    fun joinMeeting(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @Parameter(description = "참여할 정모 id", required = true)
        @PathVariable meetingId: UUID,
        @RequestBody requestDto: MeetingJoinRequestDto
    ): ResponseEntity<MeetingUsersResponseDto>

    @Operation(summary = "정모 참여정보 수정", description = "정모 참여정보 수정 설명")
    fun joinMeetingUpdate(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @Parameter(description = "변경할 정모참여 id", required = true)
        @PathVariable meetingUserId: UUID,
        @RequestBody requestDto: MeetingJoinUpdateRequestDto
    ): ResponseEntity<MeetingUsersResponseDto>

    @Operation(summary = "모든 정모 확인", description = "모든 정모 확인 설명")
    fun findAllMeetings(page: Pageable): ResponseEntity<Collection<MeetingResponseDto>>
}