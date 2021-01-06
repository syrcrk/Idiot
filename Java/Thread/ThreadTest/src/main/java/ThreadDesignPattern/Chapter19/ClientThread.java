package ThreadDesignPattern.Chapter19;

import java.util.Random;

public class ClientThread extends  Thread{
    private final RequestQueue queue;
    private final Random random;
    private final String sendValue;

    public ClientThread(RequestQueue queue,String sendValue) {
        this.queue = queue;
        this.sendValue = sendValue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println("client request "+sendValue);
            queue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
