package domain;

public class TodoItem {


    String owner;
    String deadline;
    String completed;
    String title;
    int id;
    String time;

    public TodoItem(String owner, String deadline, String completed, String title, int id, String time) {
        this.owner = owner;
        this.deadline = deadline;
        this.completed = completed;
        this.title = title;
        this.id = id;
        this.time = time;
    }

    public TodoItem(String owner, String deadline, String completed, String title, String time) {
        this.owner = owner;
        this.deadline = deadline;
        this.completed = completed;
        this.title = title;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return owner + "\n" + deadline + "\n" + completed
                + "\n" + title + "\n" + id + "\n" + time;
    }
}
