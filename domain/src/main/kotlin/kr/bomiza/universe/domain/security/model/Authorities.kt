package kr.bomiza.universe.domain.security.model

import kr.bomiza.universe.domain.security.enums.Authority

class Authorities(
    val authorities: Set<Authority>
) {
    constructor() : this(HashSet<Authority>())
    constructor(vararg authorities: Authority) : this(authorities.toSet())
    constructor(authorities: String) : this(authorities.split(", ").map { Authority.valueOf(it) }.toSet()) //runCatching

    fun joinToString(): String {
        return authorities.joinToString()
    }
}