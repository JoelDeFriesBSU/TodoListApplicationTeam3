
package DateTask;

import java.time.LocalDateTime;
import java.util.List;


public class DueDateCheck {
    LocalDateTime dueDate;
    List<LocalDateTime> allDueDates;

    public DueDateCheck(){
    }

    public DueDateCheck(LocalDateTime aDueDate) {
        this.dueDate = aDueDate;
    }


    public void addDate(){
        allDueDates.add(dueDate);

    }

    public String Snooze(int option, int id) {
        if(allDueDates.size() >0) {
            if (option == 1) {
                allDueDates.get(id).plusHours(1);
                return "Pushed back an hour";
            }
            if (option == 2) {
                allDueDates.get(id).plusHours(4);
                return "Pushed back four hours";
            }
            if (option == 3) {
                allDueDates.get(id).plusDays(1);
                return "Pushed back a day";
            }
            if (option == 4) {
                allDueDates.get(id).plusWeeks(1);
                return "Pushed back a week";
            }
            return "No option matched, please look at options again.";
        }
        return "No to do items to snooze";
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