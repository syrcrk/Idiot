package Level3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphorTest1 {
    public static void main(String[] args) {
        final Semaphore semaphore=new Semaphore(3);
        System.out.println(Thread.currentThread().getName()+" "+semaphore.availablePermits());
        new Thread(()->{
            int allPermits=semaphore.drainPermits();
            System.out.println(Thread.currentThread().getName()+" "+ allPermits);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release(3);
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" "+semaphore.availablePermits());
            semaphore.drainPermits();
            System.out.println(Thread.currentThread().getName()+" "+semaphore.availablePermits());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release(10);
            System.out.println(Thread.currentThread().getName()+" "+semaphore.availablePermits());
        }).start();

    }
}
