package kr.bomiza.universe.meeting.adapter.`in`.web

import io.swagger.v3.oas.annotations.Operation
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.request.MeetingJoinUpdateRequestDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.MeetingResponseDto
import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.MeetingUsersResponseDto
import kr.bomiza.universe.meeting.adapter.`in`.web.swagger.IMeetingController
import kr.bomiza.universe.meeting.application.legacy.MeetingService
import kr.bomiza.universe.security.domain.SecurityUser
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
class MeetingController(
    val meetingService: MeetingService,
) : IMeetingController {

    @PostMapping("/api/v1/meetings")
    @Operation(summary = "정모 생성", description = "정모생성 설명")
    override fun createMeeting(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestBody requestDto: MeetingCreateRequestDto
    ): ResponseEntity<Long> {

        val createMeeting = meetingService.createMeeting(securityUser, requestDto)

        return ResponseEntity.created(URI.create("/api/v1/meetings/${createMeeting.id}")).build()
    }

    @PostMapping("/api/v1/meetings/{meetingId}/join")
    @Operation(summary = "정모 참여", description = "정모 참여 설명")
    override fun joinMeeting(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @PathVariable meetingId: UUID,
        @RequestBody requestDto: MeetingJoinRequestDto
    ): ResponseEntity<MeetingUsersResponseDto> {

        val joinMeeting = meetingService.joinMeeting(securityUser, meetingId, requestDto)

        return ResponseEntity.ok(joinMeeting)
    }

    @PutMapping("/api/v1/meeting-users/{meetingUserId}")
    @Operation(summary = "정모 참여정보 수정", description = "정모 참여정보 수정 설명")
    override fun joinMeetingUpdate(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @PathVariable meetingUserId: UUID,
        @RequestBody requestDto: MeetingJoinUpdateRequestDto
    ): ResponseEntity<MeetingUsersResponseDto> {

        val joinMeeting = meetingService.joinMeetingUpdate(securityUser, meetingUserId, requestDto)

        return ResponseEntity.ok(joinMeeting)
    }

    @GetMapping("/api/v1/meetings")
    @Operation(summary = "모든 정모 확인", description = "모든 정모 확인 설명")
    override fun findAllMeetings(): ResponseEntity<Collection<MeetingResponseDto>> {
        val findAll = meetingService.findAll()
        return ResponseEntity.ok().body(findAll)
    }
}