package utils;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPUtils {

    HttpRequestFactory requestFactory;
    String baseURL = "https://todoserver222.herokuapp.com/";
    String todosURL = baseURL + "todos/";

    public HTTPUtils() {
        requestFactory = new NetHttpTransport().createRequestFactory();
    }

    /**
     * @param id
     * @return JSON string of the todoItem with id
     * @throws IOException
     */
    public String getTodoItemJsonString(int id) throws IOException {
        HttpRequest getRequest = requestFactory.buildGetRequest(new GenericUrl(todosURL+id));
        String rawResponse = getRequest.execute().parseAsString();
        return rawResponse;
    }

    /**
     * @param note  whatever should be in the todoItem
     * @param owner whoever is the owner of the todoItem
     * @return the ID of the recently added todoItem
     * @throws IOException
     */
    public String addTodoItem(String note, String owner,String deadline) throws IOException {
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("title",note);
        data.put("deadline",deadline);
        data.put("owner",owner);
        String time = (java.util.Calendar.getInstance().getTime()).toString();
        data.put("time created",time);
        HttpContent content = new UrlEncodedContent(data);
        HttpRequest postRequest = requestFactory.buildPostRequest(new GenericUrl(todosURL),content);
        String rawResponse = postRequest.execute().parseAsString();
        return rawResponse;
    }

}
