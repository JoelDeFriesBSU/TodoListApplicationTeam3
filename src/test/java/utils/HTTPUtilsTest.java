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
        var string = httpUtils.addTodoItem("Team 3 Test", "team3","4/20");
        var expected = "{\n" +
                "  \"title\": \"Team 3 Test\",\n" +
                "  \"deadline\": \"4/20\",\n" +
                "  \"owner\": \"team3\",\n" +
                "  \"time created\": \"now (for test)\",\n" +
                "  \"id\": " + 99 + "\n" +
                "}";
        assertEquals(expected, string);
    }
}