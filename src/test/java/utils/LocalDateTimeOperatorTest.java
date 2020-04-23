package utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeOperatorTest {

    LocalDateTimeOperator ldt = new LocalDateTimeOperator();

    @Test
    void stringToLocalDateTest() {
        String testDate = "Wed, Apr 22, 2020 -- 12:30 PM";
        LocalDateTime tempDate = ldt.stringToLocalDate(testDate);
        System.out.println(tempDate);
        String expectedDate = ldt.localDateToString(tempDate);
        assertEquals(expectedDate, testDate);
    }

    @Test
    void userInputToUsableString() {
        String month = "february";
        int day = 23;
        int year = 2000;
        int hour = 8;
        int minute = 49;
        String merdiem = "PM";
        String actual = ldt.userInputToUsableString(month, day, year, hour, minute, merdiem);
        String expected = "Wed, Feb 23, 2000 -- 08:49 PM";
        assertEquals(expected, actual);
    }

    @Test
    void convertFullMonthToShortenedTest() {
        String inputMonth = "january";
        String actual = ldt.convertFullMonthToShortened(inputMonth);
        String expected = "Jan";
        assertEquals(expected, actual);
    }

}