package Level3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) {
        final SemaphoreLock lock=new SemaphoreLock();
        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"get lock");
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        }).start();
        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"get lock");
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        }).start();
    }

    static class SemaphoreLock{
        private final Semaphore semaphore=new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public  void unLock(){
            semaphore.release();
        }
    }
}
