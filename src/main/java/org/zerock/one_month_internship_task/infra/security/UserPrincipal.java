package org.zerock.one_month_internship_task.infra.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserPrincipal(
        String username,
        Collection<GrantedAuthority> authorities
) {
}
