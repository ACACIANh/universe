package kr.bomiza.universe.meeting.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import kr.bomiza.universe.common.BaseEntity
import java.time.LocalDateTime

@Entity
class Attendance(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    var checkIn: LocalDateTime?,
    var checkOut: LocalDateTime?,

    ) : BaseEntity() {

}