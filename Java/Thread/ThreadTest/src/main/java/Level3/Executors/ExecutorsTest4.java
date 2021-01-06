package Level3.Executors;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorsTest4 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService service= Executors.newWorkStealingPool();
        List<Callable<String>> mList = IntStream.range(0,100).boxed().map(i->{
            return (Callable<String>)()->{
                Thread.sleep(100);
                System.out.println( Thread.currentThread().getName()+ " do "+i);
                return "task "+i;
            };
        }).collect(Collectors.toList());
        for (Future<String> stringFuture : service.invokeAll(mList)) {
            String s = null;
            try {
                s = stringFuture.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(s);
        }
    }
}
