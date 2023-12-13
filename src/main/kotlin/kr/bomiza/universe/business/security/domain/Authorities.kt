package kr.bomiza.universe.business.security.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class Authorities(
    val authorities: Set<Authority>
) {
    constructor() : this(HashSet<Authority>())
    constructor(vararg authorities: Authority) : this(authorities.toSet())
    constructor(authorities: String) : this(authorities.split(", ").map { Authority.valueOf(it) }.toSet()) //runCatching

    fun joinToString(): String {
        return authorities.joinToString()
    }

    fun toSimpleGrantedAuthorities(): Collection<GrantedAuthority> {
        return authorities.map { SimpleGrantedAuthority(it.name) }
    }
}