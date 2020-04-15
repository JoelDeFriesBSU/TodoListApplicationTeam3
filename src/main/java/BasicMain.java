import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import utils.HTTPUtils;

import java.io.IOException;

public class BasicMain {

    public static void main(String[] args) {
        HTTPUtils httpUtils = new HTTPUtils();
        try {
            //httpUtils.addTodoItem("Test title for id.", "team3", "4/20");
            System.out.println(httpUtils.deleteTodoItemByTitle("Get Item by Title Test", "team3"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
