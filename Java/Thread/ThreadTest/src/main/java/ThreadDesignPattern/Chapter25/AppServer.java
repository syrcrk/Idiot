package ThreadDesignPattern.Chapter25;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread {
    private int port;
    private static final int DEFAULT_PORT = 12712;
    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    ServerSocket server;
    public AppServer(int port) {
        this.port = port;
    }

    public AppServer() {
        this(DEFAULT_PORT);
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            while (start) {
                Socket client = server.accept();
                ClientHandler cl = new ClientHandler(client);
                executor.submit(cl);
                this.clientHandlers.add(cl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.dispose();
        }
    }
    private void dispose(){
        clientHandlers.stream().forEach(c->{
            c.stop();
        });
        this.executor.shutdown();
    }

    public void shutDown() throws IOException {
        this.start = false;
        this.interrupt();
        this.server.close();
    }
}
