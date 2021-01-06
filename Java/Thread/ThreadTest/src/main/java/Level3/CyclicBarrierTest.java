package Level3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(5);
        final Random rand = new Random(System.currentTimeMillis());
        IntStream.range(0, 5).forEach(i -> {
            final int index = i + 1;
            new Thread(() -> {
                try {
                    Thread.sleep(i * 1000);
                    System.out.println("thread " + index + " go to line");
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("thread " + index + " start run");
            }).start();
        });
    }
}
