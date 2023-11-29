package kr.bomiza.universe.web.controller

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.bomiza.universe.common.model.dto.meeting.*
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Meeting", description = "정모")
@SecurityRequirement(name = "Authorization")
interface IMeetingController {

    @PreAuthorize("hasRole('ADMIN')")
    fun createMeeting(@RequestBody requestDto: MeetingCreateRequestDto): ResponseEntity<Long>

    fun joinMeeting(
        @Parameter(description = "참여할 정모 id", required = true)
        @PathVariable meetingId: Long,
        @RequestBody requestDto: MeetingJoinRequestDto
    ): ResponseEntity<MeetingUsersResponseDto>

    fun joinMeetingUpdate(
        @Parameter(description = "변경할 정모참여 id", required = true)
        @PathVariable meetingUserId: Long,
        @RequestBody requestDto: MeetingJoinUpdateRequestDto
    ): ResponseEntity<MeetingUsersResponseDto>

    fun findAllMeetings(): ResponseEntity<Collection<MeetingResponseDto>>
}