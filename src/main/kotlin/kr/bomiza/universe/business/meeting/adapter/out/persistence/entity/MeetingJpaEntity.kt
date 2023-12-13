package kr.bomiza.universe.business.meeting.adapter.out.persistence.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
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

    var date: LocalDate,

    var capacityMember: Int,

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "meeting_user_id")
    var meetingUsers: List<MeetingUserJpaEntity>,

    ) : BaseEntity(id) {
}