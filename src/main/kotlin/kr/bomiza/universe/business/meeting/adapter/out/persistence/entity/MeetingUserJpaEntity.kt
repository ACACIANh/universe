package kr.bomiza.universe.business.meeting.adapter.out.persistence.entity

import jakarta.persistence.*
import kr.bomiza.universe.common.entity.BaseEntity
import kr.bomiza.universe.domain.meeting.enums.MeetingUserState
import java.time.LocalTime
import java.util.*

@Entity
@Table(name = "meeting_user")
class MeetingUserJpaEntity(

    id: UUID,

    @Column(name = "meeting_id")
    var meetingId: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserJpaEntity,

    @Enumerated(EnumType.STRING)
    var state: MeetingUserState,

    var joinTime: LocalTime,

    var guest: Boolean,

    ) : BaseEntity(id) {

}