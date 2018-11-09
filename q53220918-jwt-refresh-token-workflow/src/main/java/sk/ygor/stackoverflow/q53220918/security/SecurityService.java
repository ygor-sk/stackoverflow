package sk.ygor.stackoverflow.q53220918.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public long getLoggedUserId() {
        JWTAuthentication authentication = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getUserId();
    }

}
