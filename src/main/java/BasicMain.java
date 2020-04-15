import utils.HTTPUtils;

import java.io.IOException;

public class BasicMain {

    public static void main(String[] args) {
        HTTPUtils httpUtils = new HTTPUtils();
        try {
            //httpUtils.addTodoItem("Test title for id.", "team3", "4/20");
            System.out.println(httpUtils.deleteTodoItemByTitle("Test title for id.", "team3"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
