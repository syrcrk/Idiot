package Level3.Condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionTest3 {
    private final static ReentrantLock lock=new ReentrantLock();
    private final static Condition produceCondition=lock.newCondition();
    private final static Condition consumerCondition=lock.newCondition();

    private final static LinkedList<Long> pool=new LinkedList<>();
    private final static int MAX_COUNT=100;

    private static void produce(){ 
        try {
            lock.lock();
            while (pool.size()>MAX_COUNT){
                produceCondition.await();
            }
            long value=System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+" produce "+value);
            pool.addLast(value);
            produceCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    private static void consume(){
        try {
            lock.lock();
            while (pool.size()<=0){
                consumerCondition.await();
            }
            long value=pool.removeFirst();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+" consume "+value);
            consumerCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0,4).forEach(i->{
         new Thread(ConditionTest3::produce,"producer"+i).start();
        });
        IntStream.range(0,4).forEach(i->{
            new Thread(ConditionTest3::consume,"consumer"+i).start();
        });

    }
}
