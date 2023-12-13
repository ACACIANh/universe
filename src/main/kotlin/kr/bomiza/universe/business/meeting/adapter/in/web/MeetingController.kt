package kr.bomiza.universe.business.meeting.adapter.`in`.web

import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingCreateRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.request.MeetingJoinUpdateRequestDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.response.MeetingResponseDto
import kr.bomiza.universe.business.meeting.adapter.`in`.web.model.response.MeetingUsersResponseDto
import kr.bomiza.universe.business.meeting.application.port.`in`.CreateMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.`in`.FindMeetingUseCase
import kr.bomiza.universe.business.meeting.application.port.`in`.JoinMeetingUseCase
import kr.bomiza.universe.business.security.domain.SecurityUser
import kr.bomiza.universe.common.annotation.WebAdapter
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@WebAdapter
@RestController
class MeetingController(
    val createMeetingUseCase: CreateMeetingUseCase,
    val findMeetingUseCase: FindMeetingUseCase,
    val joinMeetingUseCase: JoinMeetingUseCase,
    val meetingMessageMapper: MeetingMessageMapper,
) : IMeetingController {

    @PostMapping("/api/v1/meetings")
    override fun createMeeting(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestBody requestDto: MeetingCreateRequestDto
    ): ResponseEntity<Long> {

        val createMeeting = createMeetingUseCase.createMeeting(securityUser.id, requestDto)

        return ResponseEntity.created(URI.create("/api/v1/meetings/${createMeeting.id}")).build()
    }

    @PostMapping("/api/v1/meetings/{meetingId}/join")
    override fun joinMeeting(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @PathVariable meetingId: UUID,
        @RequestBody requestDto: MeetingJoinRequestDto
    ): ResponseEntity<MeetingUsersResponseDto> {

        val meetingUser = joinMeetingUseCase.joinMeeting(securityUser.id, meetingId, requestDto)

        return ResponseEntity.ok(meetingMessageMapper.mapToDto(meetingUser))
    }

    @PutMapping("/api/v1/meeting-users/{meetingUserId}")
    override fun joinMeetingUpdate(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @PathVariable meetingUserId: UUID,
        @RequestBody requestDto: MeetingJoinUpdateRequestDto
    ): ResponseEntity<MeetingUsersResponseDto> {

        val meetingUser = joinMeetingUseCase.joinMeetingUpdate(securityUser.id, meetingUserId, requestDto)

        return ResponseEntity.ok(meetingMessageMapper.mapToDto(meetingUser))
    }

    @GetMapping("/api/v1/meetings")
    override fun findAllMeetings(page: Pageable): ResponseEntity<Collection<MeetingResponseDto>> {

        val meetings = findMeetingUseCase.findAll(page)

        return ResponseEntity.ok().body(meetings.map { meetingMessageMapper.mapToDto(it) })
    }
}