package utils;

import domain.TodoItem;

import java.io.*;
import java.util.ArrayList;

public class LocalSave {

    private ArrayList<TodoItem> todoList;
    private String fileName;

    public LocalSave(String aFileName){
        this.fileName = aFileName;
        this.todoList = new ArrayList<>();
    }

    // saves all current to-do items onto a file
    public boolean saveToFile(){
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
        HttpUtils = new HTTPUtils("team3");
    }

    public boolean pushAllTodoItemsToCloud(){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String w;
            while( (w = br.readLine()) != null ){
                String owner = br.readLine();
                String deadline = br.readLine();
                String completed = br.readLine();
                String title = br.readLine();
                int id = Integer.parseInt(br.readLine());
                String date = br.readLine();
                HttpUtils.addTodoItem(title,deadline);
            }
            return true;
        }catch(IOException e){
            return false;
        }
    }

    public ArrayList<TodoItem> getTodoList() {
        return todoList;
    }

    public void setTodoList(ArrayList<TodoItem> todoList) {
        this.todoList = todoList;
    }

}
