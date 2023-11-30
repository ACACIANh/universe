package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import kr.bomiza.universe.common.entity.BaseEntity
import kr.bomiza.universe.common.util.UUIDUtils
import java.time.LocalDate

@Entity
@Table(name = "meeting")
class MeetingJpaEntity(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var masterUser: UserJpaEntity,

    var date: LocalDate,

    var capacityMember: Int,

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "meeting_user_id")
    var meetingUsers: MutableList<MeetingUserJpaEntity> = mutableListOf(),

    ) : BaseEntity(UUIDUtils.generate()) {
//
//    fun checkJoinAbility(
//        user: UserJpaEntity,
//        requestDto: MeetingJoinRequestDto,
////        meetingUsers: List<MeetingUser>
//    ): Boolean {
//
//        if (ObjectUtils.isEmpty(meetingUsers)) {
//            return true;
//        }
//        val isReservedUser =
//            meetingUsers.stream().filter { e -> e.user.id == user.id && e.guest == requestDto.isGuest }
//                .findAny()
//                .isPresent
//        if (isReservedUser) {
//            throw AlreadyJoinException(this.id.toString(), user.id.toString(), requestDto.isGuest.toString())
//        }
//        return meetingUsers.size < capacityMember
//    }
//
//    fun addMeetingUser(meetingUser: MeetingUserJpaEntity) {
//        meetingUsers.add(meetingUser)
//    }
//
//    fun refreshUsers() {
//
//        meetingUsers.stream()
//            .filter {
//                it.state == MeetingUserState.PARTICIPATION || it.state == MeetingUserState.WAITING
//            }
//            .sorted(Comparator.comparing(MeetingUserJpaEntity::createdDate))
//            .limit(capacityMember.toLong())
//            .filter { it.state == MeetingUserState.WAITING }
//            .map { it.state = MeetingUserState.PARTICIPATION }
//    }
}