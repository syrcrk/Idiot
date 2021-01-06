package ThreadDesignPattern.Chapter19;

import java.util.Random;

public class ServerThread extends Thread {
    private final RequestQueue queue;
    private final Random random;

    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!flag) {
            Request req = queue.getRequest();
            if (req == null) {
                System.out.println("null request found skip");
                continue;
            }
            System.out.println("server " + req.getRequest());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
