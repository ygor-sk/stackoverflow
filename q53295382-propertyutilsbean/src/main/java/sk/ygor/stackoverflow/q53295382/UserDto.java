package sk.ygor.stackoverflow.q53295382;

public class UserDto {

    private String userId;
    private String dob;

    public UserDto() {
    }

    public UserDto(String userId, String dob) {
        this.userId = userId;
        this.dob = dob;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
