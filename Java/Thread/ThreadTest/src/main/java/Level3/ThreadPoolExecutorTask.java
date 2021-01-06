package Level3;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorTask {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0,20).boxed().forEach(i->{
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() +" finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        try {
            executorService.shutdown();
            System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
            //executorService.awaitTermination(1,TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finsihed");
    }
}
