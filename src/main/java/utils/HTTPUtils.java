package utils;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.JsonArray;
import domain.TodoItem;
import exceptions.ItemNotFoundException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HTTPUtils {

    HttpRequestFactory requestFactory;
    String baseURL = "https://todoserver222.herokuapp.com/";
    String owner = "team3/";
    String todosURL = baseURL + owner + "todos/";

    public HTTPUtils() {
        requestFactory = new NetHttpTransport().createRequestFactory();
    }

    /**
     * @param id
     * @throws IOException
     */
    public String getTodoItemJsonString(int id) throws IOException {
        HttpRequest getRequest = requestFactory.buildGetRequest(new GenericUrl(todosURL+id));
        String rawResponse = getRequest.execute().parseAsString();
        return rawResponse;
    }

    /**
     * @param title  whatever should be in the todoItem
     * @param owner whoever is the owner of the todoItem
     * @throws IOException
     */
    public String addTodoItem(String title, String owner,String deadline) throws IOException {
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("title",title);
        data.put("deadline",deadline);
        data.put("owner",owner);
        String time = (java.util.Calendar.getInstance().getTime()).toString();
        data.put("time created",time);
        HttpContent content = new UrlEncodedContent(data);
        HttpRequest postRequest = requestFactory.buildPostRequest(new GenericUrl(todosURL),content);
        String rawResponse = postRequest.execute().parseAsString();
        return rawResponse;
    }

    public static List<TodoItem> getAllTodoItemsByOwner(String owner) throws IOException {
        HttpRequestFactory requestFactory2 = new NetHttpTransport().createRequestFactory();
        HttpRequest getRequest = requestFactory2.buildGetRequest(new GenericUrl("https://todoserver222.herokuapp.com/" + owner + "/todos/"));
        String rawResponse = getRequest.execute().parseAsString();
        JsonArray jsonArray = JsonOperator.stringToJsonArray(rawResponse);
        List<TodoItem> arrayList= JsonOperator.JsonArrayToTodoItemArrayList(jsonArray);
        return arrayList;
    }

    //Temporarily added the last return statement to return the first item in the list if no item is found, need workaround.
    public TodoItem getTodoItemByTitle(String title, String owner) throws IOException {
        List<TodoItem> todoItemList = getAllTodoItemsByOwner(owner);
        try {
            for (int i = 0; i < todoItemList.size(); i++) {
                TodoItem item = todoItemList.get(i);
                if (item.getTitle() == title) {
                    return item;
                } else{
                    throw new ItemNotFoundException();
                }
            }
        } catch (ItemNotFoundException i){
            i.printStackTrace();
        }
        return todoItemList.get(0);
    }

    /**
     * Only updates local storage, still needs to be able to access internet
     * @param deleted variable is useful, but not sure how to implement right now, if at all.
     */
    public boolean deleteTodoItemByTitle(String titleOfItemToDelete, String owner) throws IOException {
        List<TodoItem> todoItemList = getAllTodoItemsByOwner(owner);
        boolean deleted = false;
        try {
            for (int i = 0; i < todoItemList.size(); i++) {
                TodoItem item = todoItemList.get(i);
                if (item.getTitle() == titleOfItemToDelete) {
                    int id = item.getId();
                    HttpRequest deleteRequest = requestFactory.buildDeleteRequest(
                            new GenericUrl(baseURL + "todos/" + id));
                    String rawResponse = deleteRequest.execute().parseAsString();
                    deleted = true;
                } else{
                    throw new ItemNotFoundException();
                }
            }
        } catch (ItemNotFoundException i){
            deleted = false;
        }
        return deleted;
    }

}
