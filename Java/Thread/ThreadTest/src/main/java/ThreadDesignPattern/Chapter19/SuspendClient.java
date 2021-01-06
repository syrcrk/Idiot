package ThreadDesignPattern.Chapter19;

public class SuspendClient {
    public static void main(String[] args) {
        final RequestQueue queue=new RequestQueue();
        ClientThread client=new ClientThread(queue,"123");
        client.start();
        ServerThread server=new ServerThread(queue);
        server.start();
        try {
            client.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.close();

    }
}
