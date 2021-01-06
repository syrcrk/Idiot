package Level3.Condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private final ReentrantLock lock=new ReentrantLock();
    private final Condition condition=lock.newCondition();
    private static int data=0;
    private static volatile boolean noUse=true;

    private  void buildData(){
        try {
            lock.lock();
            while (noUse){
                condition.await();
            }
            data++;
            Optional.of(Thread.currentThread().getName()+"  create "+data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(2);
            noUse=true;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    private void useData(){
        try {
            lock.lock();
            while (!noUse){
                condition.await();
            }
            Optional.of(Thread.currentThread().getName()+" use "+data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(2);
            noUse=false;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest test=new ConditionTest();
        new Thread(()->{
            while (true){
                test.buildData();
            }
        }).start();
        new Thread(()->{
            while (true){
                test.useData();
            }
        }).start();

        new Thread(()->{
            while (true){
                test.useData();
            }
        }).start();
    }
}
