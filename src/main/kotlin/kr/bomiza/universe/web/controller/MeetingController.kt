package kr.bomiza.universe.web.controller

import io.swagger.v3.oas.annotations.Operation
import kr.bomiza.universe.common.model.dto.meeting.*
import kr.bomiza.universe.service.MeetingService
import kr.bomiza.universe.common.util.UserContext
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class MeetingController(
    val meetingService: MeetingService,
) : IMeetingController {

    @PostMapping("/api/v1/meetings")
    @Operation(summary = "정모 생성", description = "정모생성 설명")
    override fun createMeeting(@RequestBody requestDto: MeetingCreateRequestDto): ResponseEntity<Long> {

        val user = UserContext.getCurrentUser()

        val createMeeting = meetingService.createMeeting(user, requestDto)

        return ResponseEntity.created(URI.create("/api/v1/meetings/${createMeeting.id}")).build()
    }

    @PostMapping("/api/v1/meetings/{meetingId}/join")
    @Operation(summary = "정모 참여", description = "정모 참여 설명")
    override fun joinMeeting(
        @PathVariable meetingId: Long,
        @RequestBody requestDto: MeetingJoinRequestDto
    ): ResponseEntity<MeetingUsersResponseDto> {

        val user = UserContext.getCurrentUser()

        val joinMeeting = meetingService.joinMeeting(user, meetingId, requestDto)

        return ResponseEntity.ok(joinMeeting)
    }

    @PutMapping("/api/v1/meeting-users/{meetingUserId}")
    @Operation(summary = "정모 참여정보 수정", description = "정모 참여정보 수정 설명")
    override fun joinMeetingUpdate(
        @PathVariable meetingUserId: Long,
        @RequestBody requestDto: MeetingJoinUpdateRequestDto
    ): ResponseEntity<MeetingUsersResponseDto> {

        val user = UserContext.getCurrentUser()

        val joinMeeting = meetingService.joinMeetingUpdate(user, meetingUserId, requestDto)

        return ResponseEntity.ok(joinMeeting)
    }

    @GetMapping("/api/v1/meetings")
    @Operation(summary = "모든 정모 확인", description = "모든 정모 확인 설명")
    override fun findAllMeetings(): ResponseEntity<Collection<MeetingResponseDto>> {
        val findAll = meetingService.findAll()
        return ResponseEntity.ok().body(findAll)
    }
}