import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        final AtomicInteger inte=new AtomicInteger(0);

        IntStream.range(0,10).forEach(i->{
            new Thread(()->{
                int count=10;
                while (true){
                    count--;
                    if(count<0){
                        break;
                    }
                    inte.incrementAndGet();
                    System.out.println(Thread.currentThread().getName()+" set "+inte.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"Tname "+i).start();
        });
    }

}
