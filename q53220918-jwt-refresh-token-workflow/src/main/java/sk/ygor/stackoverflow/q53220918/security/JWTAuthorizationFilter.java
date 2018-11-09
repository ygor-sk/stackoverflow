package sk.ygor.stackoverflow.q53220918.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JWTAuthorizationFilter extends GenericFilterBean {

    private static final Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private static final String HEADER_STRING = "X-Access-Token";

    @Value("${jwt.encryption.secret}")
    private String SECRET;

    @Value("${jwt.access.token.expiration.seconds}")
    private long EXPIRATION_TIME_IN_SECONDS;

    /**
     * We have made this filter responsible for creating access tokens too.
     * This way, we keep all functionality regarding JWTs in a single place.
     */
    public String generateAccessToken(long userId) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_SECONDS * 1000))
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // our filter only works with HTTP
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader(HEADER_STRING);
        if (token != null) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        }
        chain.doFilter(httpRequest, httpResponse);
    }

    private Authentication getAuthentication(String token) {
        // parse the token.
        final String username;
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET.getBytes()))
                    .build()
                    .verify(token);
            username = jwt.getSubject();
        } catch (JWTVerificationException e) {
            LOG.debug("Invalid JWT", e);
            return null;
        }

        final Long userId;
        try {
            userId = Long.valueOf(username);
        } catch (NumberFormatException e) {
            LOG.debug("Invalid JWT. Username is not an user ID");
            return null;
        }

        LOG.debug("Valid JWT. User ID: " + userId);

        return new JWTAuthentication(userId);
    }

}
