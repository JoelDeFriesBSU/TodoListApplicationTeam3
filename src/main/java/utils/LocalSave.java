package utils;

import com.google.gson.JsonObject;
import domain.TodoItem;

import java.io.*;
import java.util.ArrayList;

public class LocalSave {

    public ArrayList<TodoItem> todoList;

    public LocalSave(){}

    // saves all current to-do items onto a filename
    public boolean saveToFile(String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,false))){
            for(TodoItem e : todoList){
                bw.write(e.toString());
                bw.newLine();
            }
            return true;
        }catch(IOException e){
            return false;
        }
    }

    public void addTodoItemToList(TodoItem todoItem){
        todoList.add(todoItem);
    }

    public void removeTodoItemToList(TodoItem todoItem){
        todoList.remove(todoItem);
    }

    HTTPUtils HttpUtils;
    void setup() {
        HttpUtils = new HTTPUtils();
    }

    public boolean pushAllTodoItemsToCloud(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String w;
            while( (w = br.readLine()) != null ){
                String owner = br.readLine();
                String deadline = br.readLine();
                String completed = br.readLine();
                String title = br.readLine();
                int id = Integer.parseInt(br.readLine());
                String date = br.readLine();
                HttpUtils.addTodoItem(title,owner,deadline);
            }
            return true;
        }catch(IOException e){
            return false;
        }
    }
}
