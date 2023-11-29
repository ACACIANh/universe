package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import kr.bomiza.universe.meeting.domain.enums.MeetingUserState
import kr.bomiza.universe.common.BaseEntity
import java.time.LocalTime

@Entity
class MeetingUser(

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    var meeting: Meeting,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @Enumerated(EnumType.STRING)
    var state: MeetingUserState,

    var joinTime: LocalTime,

    var guest: Boolean,

    ) : BaseEntity() {

}