package kr.bomiza.universe.meeting.adapter.`in`.web.swagger

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingJoinUpdateRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.MeetingResponseDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.MeetingUsersResponseDto
import kr.bomiza.universe.security.domain.SecurityUser
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
    fun createMeeting(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestBody requestDto: MeetingCreateRequestDto
    ): ResponseEntity<Long>

    fun joinMeeting(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @Parameter(description = "참여할 정모 id", required = true)
        @PathVariable meetingId: UUID,
        @RequestBody requestDto: MeetingJoinRequestDto
    ): ResponseEntity<MeetingUsersResponseDto>

    fun joinMeetingUpdate(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @Parameter(description = "변경할 정모참여 id", required = true)
        @PathVariable meetingUserId: UUID,
        @RequestBody requestDto: MeetingJoinUpdateRequestDto
    ): ResponseEntity<MeetingUsersResponseDto>

    fun findAllMeetings(): ResponseEntity<Collection<MeetingResponseDto>>
}