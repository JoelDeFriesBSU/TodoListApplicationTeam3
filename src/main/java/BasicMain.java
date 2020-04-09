import utils.HTTPUtils;

import java.io.IOException;

public class BasicMain {

    public static void main(String[] args) {
        HTTPUtils httpUtils = new HTTPUtils();
        try {
            httpUtils.addTodoItem("team 3 test", "team3");
        }catch(IOException e){

        }
    }
}
