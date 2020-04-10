package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HTTPUtilsTest {

    HTTPUtils httpUtils;

    @BeforeEach
    void setup() {
        httpUtils = new HTTPUtils();
    }

    @Test // ONLY works when time is set to "now (for test)". Set time back to "time" variable otherwise!
    void addTodoItemTest() throws IOException {
        var string = httpUtils.addTodoItem("Team 3 Test", "team3","4/20");
        var expected = "{\n" +
                "  \"title\": \"Team 3 Test\",\n" +
                "  \"deadline\": \"4/20\",\n" +
                "  \"owner\": \"team3\",\n" +
                "  \"time created\": \"TIME ENTER HERE\",\n" +
                "  \"id\": 7\n" +
                "}";
        assertEquals(expected, string);
    }

    //Am terrible at figuring out tests.
    /*
    @Test
    void getAllTodoItemsTest() throws IOException {
        String owner = "team3/";
        String getItems = HTTPUtils.getAllTodoItemsByOwner(owner);
        String expected = "[\n " +
                    "{\n " +
                    "   \"title\": \"team 3 test\",\n " +
                    "   \"deadline\": \"4/20\",\n " +
                    "   \"owner\": \"team3\",\n " +
                    "   \"time created\": \"Fri Apr 10 09:10:04 EDT 2020\",\n " +
                    "   \"id\": 4\n  " +
                    "},\n  " +
                    "{\n " +
                    "   \"title\": \"Test todo list.\",\n " +
                    "   \"deadline\": \"4/15\",\n " +
                    "   \"owner\": \"team3\",\n " +
                    "   \"time created\": \"Fri Apr 10 09:14:33 EDT 2020\",\n " +
                    "   \"id\": 5\n  " +
                    "},\n  " +
                    "{\n " +
                    "   \"title\": \"Team 3 Test\",\n " +
                    "   \"deadline\": \"4/20\",\n " +
                    "   \"owner\": \"team3\",\n " +
                    "   \"time created\": \"Fri Apr 10 09:38:52 EDT 2020\",\n " +
                    "   \"id\": 6\n  " +
                    "}\n " +
                "]";
        assertEquals(expected, getItems);
    }
     */
}