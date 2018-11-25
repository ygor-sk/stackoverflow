package sk.ygor.stackoverflow.q53387965;

import java.util.Date;

public class Task {
    private final String title;
    private final String desc;
    private final Date targetDate;

    public Task(String title, String desc, Date targetDate) {

        this.title = title;
        this.desc = desc;
        this.targetDate = targetDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    @Override public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", targetDate=" + targetDate +
                '}';
    }
}
