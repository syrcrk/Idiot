package ThreadDesignPattern.Chapter26;

import java.util.Random;

public class WorkerThread extends Thread {
    private final Channel channel;
    private static final Random rand=new Random(System.currentTimeMillis());
    public WorkerThread(String name,Channel channel) {
        super(name);
        this.channel=channel;
    }

    @Override
    public void run() {
        while (true){
            try {
                Request request= channel.take();
                request.execute();
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
