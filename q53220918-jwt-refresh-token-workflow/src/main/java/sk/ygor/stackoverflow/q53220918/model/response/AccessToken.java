package sk.ygor.stackoverflow.q53220918.model.response;

public class AccessToken {

    private final String jwt;

    public AccessToken(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
