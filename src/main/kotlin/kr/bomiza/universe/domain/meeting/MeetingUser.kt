package kr.bomiza.universe.domain.meeting

import com.fasterxml.jackson.annotation.JsonBackReference
import kr.bomiza.universe.common.model.enums.MeetingUserState
import kr.bomiza.universe.domain.BaseEntity
import kr.bomiza.universe.domain.user.User
import java.time.LocalTime
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

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