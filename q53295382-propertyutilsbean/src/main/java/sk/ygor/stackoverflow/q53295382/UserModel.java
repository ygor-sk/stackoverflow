package sk.ygor.stackoverflow.q53295382;

import java.util.Date;

public class UserModel {

    private int userId;
    private Date dob;

    public UserModel() {
    }

    public UserModel(int userId, Date dob) {
        this.userId = userId;
        this.dob = dob;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", dob=" + dob +
                '}';
    }
}
