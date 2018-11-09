package sk.ygor.stackoverflow.q53207105;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SomePOJO {

    @NotNull
    @Size(min =2, max=50)
    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String country;

    @Size(min =2,max=50)
    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String state;

    public SomePOJO(String country, String state) {
        this.country = country;
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

}
