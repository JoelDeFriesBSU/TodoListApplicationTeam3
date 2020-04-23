package utils;

import com.google.gson.*;
import domain.TodoItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonOperator {

    public static JsonArray stringToJsonArray(String stringToConvertToJson){
        JsonParser jsonParser = new JsonParser();
        JsonElement stringToJsonElement = jsonParser.parse(stringToConvertToJson);
        JsonArray stringToJsonArray = stringToJsonElement.getAsJsonArray();
        return stringToJsonArray;
    }

    public static JsonObject stringToJsonObject(String stringToConvertToJson){
        JsonParser jsonParser = new JsonParser();
        JsonElement stringToJsonElement = jsonParser.parse(stringToConvertToJson);
        JsonObject stringToJsonObject = stringToJsonElement.getAsJsonObject();
        return stringToJsonObject;
    }

    public static List<TodoItem> JsonArrayToTodoItemArrayList(JsonArray jsonArray){
        List<TodoItem> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            //if statement to keep from deleting the first 3 objects in the list, which are Hergin's
            int testInt = jsonObject.getAsJsonPrimitive("id").getAsInt();
            if(testInt < 4){
            } else {
                TodoItem todoItem = convertJsonObjectToTodoItem(jsonObject);
                arrayList.add(todoItem);
            }
        }
        return arrayList;
    }

    public static TodoItem convertJsonObjectToTodoItem(JsonObject jsonObject){
        var owner = jsonObject.getAsJsonPrimitive("owner").getAsString();
        var deadline = jsonObject.getAsJsonPrimitive("deadline").getAsString();
        var completed = jsonObject.getAsJsonPrimitive("date completed").getAsString();
        var title = jsonObject.getAsJsonPrimitive("title").getAsString();
        int id = jsonObject.getAsJsonPrimitive("id").getAsInt();
        var time = jsonObject.getAsJsonPrimitive("time created").getAsString();
        TodoItem todoItem = new TodoItem(owner, deadline, completed, title, id, time);
        return todoItem;
    }

}
