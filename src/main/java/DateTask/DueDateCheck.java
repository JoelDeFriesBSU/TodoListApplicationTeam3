
package DateTask;

import java.time.LocalDateTime;
import java.util.List;


public class DueDateCheck {
    LocalDateTime main;
    LocalDateTime last;
    List<LocalDateTime> DueDate = List.of();


    public DueDateCheck(LocalDateTime main) {
        this.main = main;
        DueDate.add(main);
    }

    public String DueDate() {
        return "Your task is due at " + main;
    }

    public void Snooze(int option) {

        if (option == 1) {
            main.plusHours(1);
        }

        if (option == 2) {
            main.plusHours(4);
        }

        if (option == 3) {
            main.plusDays(1);
        }

        if (option == 4) {
            main.plusWeeks(1);
        }
    }

    public String DayLeftNotification() {
        return "Hello today is" + main.minusDays(1) + "which means you have one day to complete your task";
    }

    public String HourLeftNotification() {
        return "Hello today is" +main.minusHours(1) + "which means you have an hour left to complete your task";
    }

    public void CompletionOfTask(LocalDateTime completedTime) {

        last = completedTime;

    }


}