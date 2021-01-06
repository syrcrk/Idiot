package ThreadDesignPattern.Chapter22;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Thread{
    private final MessageQueue queue;
    private final Random random=new Random(System.currentTimeMillis());
    private static AtomicInteger counter=new AtomicInteger(0);
    public Producer(MessageQueue queue,int index) {
        super("Producer "+index);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message msg=new Message(""+counter.getAndIncrement());
                queue.put(msg);
                System.out.println(Thread.currentThread().getName()+" produce msg "+msg.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
