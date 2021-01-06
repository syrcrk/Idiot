package Level3.Executors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorsTest3 {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(1);
        IntStream.range(0,1000).boxed().forEach(i->{
            final int id=i;
            service.submit(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" done "+id);
            });
        });
    }
}
