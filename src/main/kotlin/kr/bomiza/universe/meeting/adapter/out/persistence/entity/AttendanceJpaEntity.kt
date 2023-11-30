package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import jakarta.persistence.*
import kr.bomiza.universe.common.entity.BaseEntity
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "attendance")
class AttendanceJpaEntity(

    @Id
    override var id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserJpaEntity,

    var checkIn: LocalDateTime?,
    var checkOut: LocalDateTime?,

    ) : BaseEntity(id) {

}