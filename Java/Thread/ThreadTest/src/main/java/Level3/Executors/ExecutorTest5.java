package Level3.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorTest5 {
    public static void main(String[] args) throws InterruptedException {
        //isShutDown();
        //isTerminated();
        executeRunnableError();
    }

    static void isShutDown(){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Done job");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(service.isShutdown());
        service.shutdown();
        System.out.println(service.isShutdown());
        service.execute(()->{System.out.println("run1");});
    }

    static void isTerminated(){
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Done job");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        service.shutdown();
        System.out.println(service.isShutdown());
        System.out.println(((ThreadPoolExecutor)service).isTerminating());
    }


    private static void executeRunnableError() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        IntStream.range(0,20).boxed().forEach(i-> service.execute(()->System.out.println(i/0)));
        service.shutdown();
        service.awaitTermination(10,TimeUnit.MINUTES);
        System.out.println("********************");
    }
}
