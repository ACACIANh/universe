package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import kr.bomiza.universe.common.entity.BaseEntity
import kr.bomiza.universe.common.util.UUIDUtils
import kr.bomiza.universe.meeting.domain.enums.MeetingUserState
import java.time.LocalTime

@Entity
@Table(name = "meeting_user")
class MeetingUserJpaEntity(

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    var meeting: MeetingJpaEntity,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserJpaEntity,

    @Enumerated(EnumType.STRING)
    var state: MeetingUserState,

    var joinTime: LocalTime,

    var guest: Boolean,

    ) : BaseEntity(UUIDUtils.generate()) {

}