package utils;

import com.google.gson.*;
import domain.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class JsonOperator {

    public static JsonArray stringToJsonArray(String stringToConvertToJson){
        JsonParser jsonParser = new JsonParser();
        JsonElement stringToJsonElement = jsonParser.parse(stringToConvertToJson);
        JsonArray stringToJsonArray = stringToJsonElement.getAsJsonArray();
        return stringToJsonArray;
    }

    public static List<TodoItem> JsonArrayToTodoItemArrayList(JsonArray jsonArray){
        List<TodoItem> jsonList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            TodoItem todoItem = convertJsonObjectToTodoItem(jsonObject);
            jsonList.add(todoItem);
        }
        return jsonList;
    }

    public static TodoItem convertJsonObjectToTodoItem(JsonObject jsonObject){
        var owner = jsonObject.getAsJsonPrimitive("owner").getAsString();
        var todo = jsonObject.getAsJsonPrimitive("title").getAsString();
        int id = jsonObject.getAsJsonPrimitive("id").getAsInt();
        var time = jsonObject.getAsJsonPrimitive("time created").getAsString();
        TodoItem todoItem = new TodoItem(owner, todo, id, time);
        return todoItem;
    }

}
