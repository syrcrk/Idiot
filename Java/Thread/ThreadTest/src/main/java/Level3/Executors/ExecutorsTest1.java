package Level3.Executors;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorsTest1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor)service).getActiveCount());
        IntStream.range(0,10).boxed().forEach(i->{
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"  "+
                        ((ThreadPoolExecutor)service).getActiveCount()+"  "+i);
            });
        });
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.range(0,10).boxed().forEach(i->{
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"  "+
                        ((ThreadPoolExecutor)service).getActiveCount()+"  "+i);
            });
        });
        Thread.sleep(5_000);
        System.out.println(Thread.currentThread().getName()+"  "+
                ((ThreadPoolExecutor)service).getActiveCount());

    }
}
