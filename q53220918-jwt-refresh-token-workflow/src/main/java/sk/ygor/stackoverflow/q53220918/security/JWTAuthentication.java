package sk.ygor.stackoverflow.q53220918.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Represents all information, which has been extracted from JWT (currently only userId)
 */
public class JWTAuthentication implements Authentication {

    private final long userId;

    public JWTAuthentication(long userId) {
        this.userId = userId;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override public Object getCredentials() {
        return null;
    }

    @Override public Object getDetails() {
        return null;
    }

    public long getUserId() {
        return userId;
    }

    @Override public Long getPrincipal() {
        return userId;
    }

    @Override public boolean isAuthenticated() {
        return true;
    }

    @Override public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException("JWT authentication is always authenticated");
    }

    @Override public String getName() {
        return String.valueOf(userId);
    }
}
