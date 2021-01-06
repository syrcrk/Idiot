package Level3.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorsTest2 {
    public static void main(String[] args) {
        ExecutorService service= Executors.newSingleThreadExecutor();

        IntStream.range(0,10).boxed().forEach(i->{
            final int id=i;
            service.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" done "+id);
            });
        });
    }
}
