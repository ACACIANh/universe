package kr.bomiza.universe.domain.common.model

import kr.bomiza.universe.domain.common.util.UUIDUtils
import java.util.*

abstract class Base(
    val id: UUID
) {
    companion object {
        fun newInstance(): UUID {
            return UUIDUtils.generate()
        }
    }

    fun idString() = id.toString()
    fun equalsId(id: UUID) = this.id == id
    fun equalsId(other: Base) = this.id == other.id
}
