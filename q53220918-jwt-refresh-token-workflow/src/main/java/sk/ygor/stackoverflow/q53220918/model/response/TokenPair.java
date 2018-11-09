package sk.ygor.stackoverflow.q53220918.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenPair {

    private final String jwt;

    @JsonProperty("refresh_token")
    private final String refreshToken;

    public TokenPair(String jwt, String refreshToken) {
        this.jwt = jwt;
        this.refreshToken = refreshToken;
    }

    public String getJwt() {
        return jwt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

}
