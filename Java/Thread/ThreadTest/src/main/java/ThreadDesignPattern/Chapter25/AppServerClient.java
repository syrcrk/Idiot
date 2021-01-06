package ThreadDesignPattern.Chapter25;

import java.io.IOException;

public class AppServerClient {
    public static void main(String[] args) {
        AppServer server=new AppServer(13345);
        server.start();
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            server.shutDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
