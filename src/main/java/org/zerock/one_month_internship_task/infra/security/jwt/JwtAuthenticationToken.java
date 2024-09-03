package org.zerock.one_month_internship_task.infra.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.zerock.one_month_internship_task.infra.security.UserPrincipal;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrincipal userPrincipal;


    public JwtAuthenticationToken(UserPrincipal userPrincipal) {
        super(userPrincipal.authorities());
        this.userPrincipal = userPrincipal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userPrincipal;
    }
}
