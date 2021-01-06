package ThreadDesignPattern.Chapter23;

import java.util.Random;
import java.util.stream.IntStream;

public class JDKCounterDown {
    private static final Random random=new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final MyCounterDown counterDown=new MyCounterDown(10);
        System.out.println("start 1");
        IntStream.range(0,10).forEach(i->{
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+" is working");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counterDown.countDown();
            },String.valueOf(i)).start();
        });
        try {
            counterDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start 2");
    }
}
