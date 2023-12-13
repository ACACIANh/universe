package kr.bomiza.universe.business.security.domain

import kr.bomiza.universe.domain.common.model.Base
import kr.bomiza.universe.domain.common.util.UUIDUtils
import java.util.*

class SecurityUser(
    id: UUID,
    val email: String,
    val authorities: Authorities
) : Base(id) {

    constructor(email: String, authorities: Authorities) : this(UUIDUtils.generate(), email, authorities)
}