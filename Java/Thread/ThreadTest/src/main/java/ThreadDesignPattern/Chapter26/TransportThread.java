package ThreadDesignPattern.Chapter26;

import java.util.Random;

public class TransportThread extends Thread {
    private final Channel channel;
    private static final Random rand=new Random(System.currentTimeMillis());
    public TransportThread(String name,Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        for(int i=0;true;i++){
            try {
                Request res=new Request(getName(),i);
                this.channel.put(res);
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
