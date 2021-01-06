package ThreadDesignPattern.Chapter22;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Thread {
    private final MessageQueue queue;
    private final Random random=new Random(System.currentTimeMillis());
    private static AtomicInteger counter=new AtomicInteger(0);
    public Consumer(MessageQueue queue,int index) {
        super("Consumer "+index);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message msg=queue.take();
                System.out.println(Thread.currentThread().getName()+" consume msg "+msg.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
