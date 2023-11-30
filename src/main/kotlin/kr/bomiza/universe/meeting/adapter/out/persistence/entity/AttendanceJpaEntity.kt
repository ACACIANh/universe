package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import jakarta.persistence.*
import kr.bomiza.universe.common.entity.BaseEntity
import kr.bomiza.universe.common.util.UUIDUtils
import java.time.LocalDateTime

@Entity
@Table(name = "attendance")
class AttendanceJpaEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserJpaEntity,

    var checkIn: LocalDateTime?,
    var checkOut: LocalDateTime?,

    ) : BaseEntity(UUIDUtils.generate()) {

}