package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeOperator {

    private String dateTimeFormat;

    public LocalDateTimeOperator(){
        dateTimeFormat = "EEE, LLL dd, yyyy -- hh:mm a";
    }

    public LocalDateTime stringToLocalDate(String dateString){
        LocalDateTime dateLDT = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(dateTimeFormat));
        return dateLDT;
    }

    public String localDateToString(LocalDateTime dateTime){
        String dateString = dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
        return dateString;
    }

    public String userInputToUsableString(String aMonth, int aDay, int aYear, int aHour, int aMinute, String aMeridiem){
        aMonth = convertFullMonthToShortened(aMonth);
        String dayOfWeek = convertFullDayToShortened(LocalDate.ofYearDay(2000, 54).getDayOfWeek().toString());
        String beforeHour = checkIfHourNeedsZero(aHour);
        String usableString = dayOfWeek + ", " + aMonth + " " + aDay + ", " + aYear + " -- " + beforeHour + aHour + ":" + aMinute + " " + aMeridiem;
        return usableString;
    }

    public String convertFullMonthToShortened(String aMonth) {
        aMonth = aMonth.substring(0, 1).toUpperCase() + aMonth.substring(1, 3);
        return aMonth;
    }

    public String convertFullDayToShortened(String aDay){
        aDay = aDay.substring(0, 1) + aDay.substring(1, 3).toLowerCase();
        return aDay;
    }

    public String checkIfHourNeedsZero(int aHour){
        if(aHour < 10){
            return "0";
        }
        return "";
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

}
