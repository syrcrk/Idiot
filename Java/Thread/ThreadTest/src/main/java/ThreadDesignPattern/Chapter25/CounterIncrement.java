package ThreadDesignPattern.Chapter25;

import java.util.Random;

public class CounterIncrement extends Thread {
    private volatile boolean terminated=false;
    private int counter;
    private Random rand=new Random(System.currentTimeMillis());
    @Override
    public void run() {
        try {
            while (!terminated){
                System.out.println("add "+counter++);
                Thread.sleep(rand.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            clean();
        }
    }

    public void clean(){
        System.out.println("do clean work "+counter );
    }

    public void close(){
        this.terminated=true;
        interrupt();
    }
}
