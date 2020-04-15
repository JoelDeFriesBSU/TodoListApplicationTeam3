package DateTask;

import java.time.LocalDate;
import java.time.LocalTime;

public class DueDateCheck {
    
        LocalDate date;
        LocalTime time;


        public DueDateCheck(LocalDate date, LocalTime time){
            this.date = date;
            this.time = time;
        }

        public String DueDate(){
            return "Your task is due" + date + "at this time" + time;
        }

        public void Snooze(int option){

            if(option == 1){
                time.plusHours(1);
            }

            if(option == 2){
                time.plusHours(4);
            }

            if(option == 3){
                date.plusDays(1);
            }

            if(option == 4){
                date.plusWeeks(1);
            }
        }

        public String DayLeftNotification(){
            return "Hello today is"+date.minusDays(1) + "which means you have one day to complete your task";
        }

        public String HourLeftNotification(){
            return "Hello today is"+date + time.minusHours(1)+ "which means you have an hour left to complete your task";
        }
}
