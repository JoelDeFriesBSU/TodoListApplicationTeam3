import utils.HTTPUtils;

import java.io.IOException;

public class BasicMain {

    public static void main(String[] args) {
        HTTPUtils httpUtils = new HTTPUtils();
        try {
            System.out.println(httpUtils.getAllTodoItemsByOwner("team3/"));
        }catch(IOException e){

        }
    }
}
