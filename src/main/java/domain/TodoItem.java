package domain;

public class TodoItem {


    String owner;
    String deadline;
    String dateCompleted;
    String title;
    int id;
    String dateAdded;

    public TodoItem(String owner, String deadline, String dateCompleted, String title, int id, String dateAdded) {
        this.owner = owner;
        this.deadline = deadline;
        this.dateCompleted = dateCompleted;
        this.title = title;
        this.id = id;
        this.dateAdded = dateAdded;
    }

    public TodoItem(String owner, String deadline, String dateCompleted, String title, String dateAdded) {
        this.owner = owner;
        this.deadline = deadline;
        this.dateCompleted = dateCompleted;
        this.title = title;
        this.dateAdded = dateAdded;
    }

    public String getDateAdded() {
        return dateAdded;
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

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Override
    public String toString() {
        return owner + "\n" + deadline + "\n" + dateCompleted
                + "\n" + title + "\n" + id + "\n" + dateAdded;
    }
}
