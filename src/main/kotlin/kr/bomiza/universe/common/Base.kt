package kr.bomiza.universe.common

import kr.bomiza.universe.common.util.UUIDUtils
import java.util.*

abstract class Base {
    val id: UUID

    constructor() {
        this.id = UUIDUtils.generate()
    }

    constructor(id: UUID) {
        this.id = id
    }
}
