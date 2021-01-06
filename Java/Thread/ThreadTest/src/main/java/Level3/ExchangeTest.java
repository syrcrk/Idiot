package Level3;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangeTest {
    public static void main(String[] args) {
        final Exchanger<String> exchange=new Exchanger<>();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                String fromB=exchange.exchange("I come fron T1");
                System.out.println(Thread.currentThread().getName()+ fromB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                String fromB=exchange.exchange("I come fron T2");
                System.out.println(Thread.currentThread().getName()+ fromB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();

    }
}
