package sk.ygor.stackoverflow.q53220918.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RefreshToken {

    @NotBlank
    @JsonProperty("refresh_token")
    private final String refreshToken;

    @JsonCreator
    public RefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

}
