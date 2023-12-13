package kr.bomiza.universe.business.security.domain

import kr.bomiza.universe.domain.security.model.Authorities
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

fun Authorities.toSimpleGrantedAuthorities(): Collection<GrantedAuthority> {
    return authorities.map { SimpleGrantedAuthority(it.name) }
}