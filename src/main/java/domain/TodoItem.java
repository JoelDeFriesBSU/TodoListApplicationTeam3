package domain;

public class TodoItem {


    String owner;
    String todo;
    int id;
    String time;

    public TodoItem(String owner, String todo, int id, String time) {
        this.owner = owner;
        this.todo = todo;
        this.id = id;
        this.time = time;
    }

    public TodoItem(String owner, String todo, String time) {
        this.owner = owner;
        this.todo = todo;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getOwner() {
        return owner;
    }

    public String getTodo() {
        return todo;
    }

    public int getId() {
        return id;
    }
}
