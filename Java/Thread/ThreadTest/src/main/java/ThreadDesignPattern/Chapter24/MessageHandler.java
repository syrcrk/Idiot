package ThreadDesignPattern.Chapter24;

import ThreadDesignPattern.Chapter22.Message;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {
    private final static Executor executor= Executors.newFixedThreadPool(5);
    public void request(Message msg){
        executor.execute(()->{
            try {
                String value=msg.getData();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( Thread.currentThread().getName()+" done msg "+msg.getData());
        });
    }

    public void shutDown(){
        ((ExecutorService)executor).shutdown();
    }
}
