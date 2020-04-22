import utils.HTTPUtils;

import java.io.IOException;

public class BasicMain {

    public static void main(String[] args) {
        HTTPUtils httpUtils = new HTTPUtils("team3");
        try {
            //System.out.println(httpUtils.deleteAllItems());
            System.out.println(httpUtils.addTodoItem("TodoItem", "4/20"));
            System.out.println(httpUtils.deleteTodoItemByTitle("TodoItem"));
            //System.out.println(httpUtils.deleteTodoItemByTitle("TodoItem"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
