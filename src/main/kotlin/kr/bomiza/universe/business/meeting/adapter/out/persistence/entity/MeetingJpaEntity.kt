package kr.bomiza.universe.business.meeting.adapter.out.persistence.entity

import jakarta.persistence.*
import kr.bomiza.universe.common.entity.BaseEntity
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "meeting")
class MeetingJpaEntity(

    id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var masterUser: UserJpaEntity,

    @Column(unique = true)
    var date: LocalDate,

    var capacityMember: Int,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], targetEntity = MeetingUserJpaEntity::class)
    @JoinColumn(name = "meeting_id")
    var meetingUsers: List<MeetingUserJpaEntity>,

    ) : BaseEntity(id) {
}