
package DateTask;

import java.time.LocalDateTime;


public class DueDateCheck {
    LocalDateTime dueDate;

    public DueDateCheck(LocalDateTime aDueDate) {
        this.dueDate = aDueDate;
    }

    public void Snooze(int option) {
        if (option == 1) {
            dueDate.plusHours(1);
        }
        if (option == 2) {
            dueDate.plusHours(4);
        }
        if (option == 3) {
            dueDate.plusDays(1);
        }
        if (option == 4) {
            dueDate.plusWeeks(1);
        }
    }

    public boolean DayLeftNotification() {
        if(dueDate.minusDays(1) == LocalDateTime.now()){
            return true;
        }
        return false;
    }

    public boolean HourLeftNotification() {
        if (dueDate.minusHours(1) == LocalDateTime.now()) {
            return true;
        }
        return false;
    }

    public LocalDateTime CompleteTodoItem() {
        LocalDateTime completedTime = LocalDateTime.now();
        return completedTime;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }


}