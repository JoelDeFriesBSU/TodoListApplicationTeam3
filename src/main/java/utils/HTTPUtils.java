package utils;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.JsonArray;
import domain.TodoItem;
import exceptions.ItemNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HTTPUtils {

    HttpRequestFactory requestFactory;
    String baseURL = "https://todoserver222.herokuapp.com/";
    String ownerURLTag = "team3/";
    String todosURL = baseURL + ownerURLTag + "todos/";
    String owner;
    LocalSave ls = new LocalSave("localitems.txt");

    public HTTPUtils(String aOwner) {
        this.owner = aOwner;
        requestFactory = new NetHttpTransport().createRequestFactory();
    }

    public String getJsonStringFromInternet() throws IOException {
        HttpRequest getRequest = requestFactory.buildGetRequest(new GenericUrl(todosURL));
        String rawResponse = getRequest.execute().parseAsString();
        return rawResponse;
    }

    public String addTodoItem(String title, String deadline) throws IOException {
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("title",title);
        data.put("deadline",deadline);
        data.put("date completed", "0");
        data.put("owner",owner);
        String time = (java.util.Calendar.getInstance().getTime()).toString();
        data.put("time created",time);
        HttpContent content = new UrlEncodedContent(data);
        HttpRequest postRequest = requestFactory.buildPostRequest(new GenericUrl(todosURL),content);
        String rawResponse = postRequest.execute().parseAsString();
        ls.addTodoItemToList(new TodoItem(title,deadline,"",owner,time));
        ls.saveToFile();
        return rawResponse;
    }

    public boolean deleteTodoItemByTitle(String titleOfItemToDelete) throws IOException {
        List<TodoItem> todoItemList = getAllTodoItems();
        try {
            for (int i = 0; i < todoItemList.size(); i++) {
                TodoItem item = todoItemList.get(i);
                if (item.getTitle().equals(titleOfItemToDelete)) {
                    int id = item.getId();
                    HttpRequest deleteRequest = requestFactory.buildDeleteRequest(
                            new GenericUrl(baseURL + "todos/" + id));
                    String rawResponse = deleteRequest.execute().parseAsString();
                    ls.removeTodoItemToList(item);
                    ls.saveToFile();
                    return true;
                }
            }
            throw new ItemNotFoundException();
        } catch (ItemNotFoundException i){
            return false;
        }
    }

    //Last return statement should never be accessed, but the IDEA complained without it.
    public TodoItem getTodoItemByTitle(String title) throws IOException {
        List<TodoItem> todoItemList = getAllTodoItems();
        try {
            for (int i = 0; i < todoItemList.size(); i++) {
                TodoItem item = todoItemList.get(i);
                if (item.getTitle().equals(title)) {
                    return item;
                }
            }
            throw new ItemNotFoundException();
        } catch (ItemNotFoundException i){
            i.printStackTrace();
        }
        String time = (java.util.Calendar.getInstance().getTime()).toString();
        return new TodoItem("dev", "0","0", "Item not Found", time);
    }

    public void setLocalSaveArrayList(List<TodoItem> arrayList){
        ArrayList<TodoItem> newList = (ArrayList<TodoItem>) arrayList;
        ls.setTodoList(newList);
    }

    public List<TodoItem> getAllTodoItems() throws IOException {
        String rawResponse = getJsonStringFromInternet();
        JsonArray jsonArray = JsonOperator.stringToJsonArray(rawResponse);
        List<TodoItem> arrayList = JsonOperator.JsonArrayToTodoItemArrayList(jsonArray);
        setLocalSaveArrayList(arrayList);
        return arrayList;
    }

    public static boolean deleteAllItems() throws IOException {
        try {
            HttpRequestFactory requestFactory2 = new NetHttpTransport().createRequestFactory();
            HttpRequest deleteRequest = requestFactory2.buildDeleteRequest(
                    new GenericUrl("https://todoserver222.herokuapp.com/team3/todos"));
            String rawResponse = deleteRequest.execute().parseAsString();
            return true;
        } catch(IOException e){
            return false;
        }

    }
}
