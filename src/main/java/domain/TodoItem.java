package domain;

import DateTask.DueDateCheck;
import utils.LocalDateTimeOperator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoItem {


    String owner;
    LocalDateTime dueDate;
    String completionDate;
    String title;
    int id;
    String dateAdded;
    LocalDateTimeOperator ldt = new LocalDateTimeOperator();
    DueDateCheck dateChecker;

    public TodoItem(String owner, String dueDate, String completionDate, String title, int id, String dateAdded) {
        this.owner = owner;
        this.dueDate = ldt.stringToLocalDate(dueDate);
        this.completionDate = completionDate;
        this.title = title;
        this.id = id;
        this.dateAdded = dateAdded;
        this.dateChecker = new DueDateCheck(this.dueDate);
    }

    public String getOwner() {
        return owner;
    }

    public LocalDateTime getDueDate() {
        return dateChecker.getDueDate();
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate() {
        this.completionDate = ldt.localDateToString(dateChecker.CompleteTodoItem());
    }

    @Override
    public String toString() {
        return owner + "\n" + dueDate.format(DateTimeFormatter.ofPattern(ldt.getDateTimeFormat())) + "\n" + completionDate
                + "\n" + title + "\n" + id + "\n" + dateAdded;
    }
}
