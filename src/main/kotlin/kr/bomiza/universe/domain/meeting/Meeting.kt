package kr.bomiza.universe.domain.meeting

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import kr.bomiza.universe.common.model.dto.meeting.MeetingJoinRequestDto
import kr.bomiza.universe.common.model.enums.MeetingUserState
import kr.bomiza.universe.common.model.exception.AlreadyJoinException
import kr.bomiza.universe.domain.BaseEntity
import kr.bomiza.universe.domain.user.User
import org.springframework.util.ObjectUtils
import java.time.LocalDate

@Entity
class Meeting(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var masterUser: User,

    var date: LocalDate,

    var capacityMember: Int,

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "meeting_user_id")
    var meetingUsers: MutableList<MeetingUser> = mutableListOf(),

    ) : BaseEntity() {

    fun checkJoinAbility(
        user: User,
        requestDto: MeetingJoinRequestDto,
//        meetingUsers: List<MeetingUser>
    ): Boolean {

        if (ObjectUtils.isEmpty(meetingUsers)) {
            return true;
        }
        val isReservedUser =
            meetingUsers.stream().filter { e -> e.user.id == user.id && e.guest == requestDto.isGuest }
                .findAny()
                .isPresent
        if (isReservedUser) {
            throw AlreadyJoinException(this.id.toString(), user.id.toString(), requestDto.isGuest.toString())
        }
        return meetingUsers.size < capacityMember
    }

    fun addMeetingUser(meetingUser: MeetingUser) {
        meetingUsers.add(meetingUser)
    }

    fun refreshUsers() {

        meetingUsers.stream()
            .filter {
                it.state == MeetingUserState.PARTICIPATION || it.state == MeetingUserState.WAITING
            }
            .sorted(Comparator.comparing(MeetingUser::createdDate))
            .limit(capacityMember.toLong())
            .filter { it.state == MeetingUserState.WAITING }
            .map { it.state = MeetingUserState.PARTICIPATION }
    }
}