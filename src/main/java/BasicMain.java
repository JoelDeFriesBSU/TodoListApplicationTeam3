import utils.HTTPUtils;

import java.io.IOException;

public class BasicMain {

    public static void main(String[] args) {
        HTTPUtils httpUtils = new HTTPUtils("team3");
        try {
            //System.out.println(httpUtils.deleteAllItems());
            System.out.println(httpUtils.addTodoItem("TodoItem", "Mon, Apr 20, 2020 -- 12:30 PM"));
            System.out.println(httpUtils.deleteTodoItemByTitle("TodoItem"));
            //System.out.println(httpUtils.deleteTodoItemByTitle("TodoItem"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
