package kr.bomiza.universe.security.domain

import kr.bomiza.universe.common.Base
import kr.bomiza.universe.common.util.UUIDUtils
import java.util.*

class SecurityUser(
    id: UUID,
    val email: String,
    val authorities: Authorities
) : Base(id) {

    constructor(email: String, authorities: Authorities) : this(UUIDUtils.generate(), email, authorities)
}