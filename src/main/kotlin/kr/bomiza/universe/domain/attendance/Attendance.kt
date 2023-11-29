package kr.bomiza.universe.domain.attendance

import kr.bomiza.universe.domain.BaseEntity
import kr.bomiza.universe.domain.user.User
import java.time.LocalDateTime
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity
class Attendance(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    var checkIn: LocalDateTime?,
    var checkOut: LocalDateTime?,

    ) : BaseEntity() {

}