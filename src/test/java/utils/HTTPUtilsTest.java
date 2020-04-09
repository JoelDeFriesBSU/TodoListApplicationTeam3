package utils;

import org.apache.http.protocol.HTTP;
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
    void addTodoItem() throws IOException {
        var resultingID = httpUtils.addTodoItem("Team 3 Test", "team3");
        var expected = "{\n" +
                "  \"title\": \"Team 3 Test\",\n" +
                "  \"owner\": \"team3\",\n" +
                "  \"time created\": \"now (for test)\",\n" +
                "  \"id\": " + resultingID + "\n" +
                "}";
        var actual = httpUtils.getTodoItemJsonString(resultingID);
        assertEquals(expected, actual);
    }

}