package kr.bomiza.universe.common

import kr.bomiza.universe.common.util.UUIDUtils
import java.util.*

//todo: 이렇게까지 해야되는지에 대한 고민하기.. Id 박아버리는것과 비교
abstract class Base(
    val id: UUID
) {
    companion object {
        fun newInstance(): UUID {
            return UUIDUtils.generate()
        }
    }
}
