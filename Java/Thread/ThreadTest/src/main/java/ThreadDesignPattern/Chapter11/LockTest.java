package ThreadDesignPattern.Chapter11;

import java.util.stream.Stream;

public class LockTest {
    public static final void main(String[] args){
        final BoolLock boolLock=new BoolLock();
        Stream.of("T1","T2","T3","T4","T5","T6").forEach(tName->{
            new Thread(()->{
                try {
                    boolLock.lock(2000);
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+" work done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (CLock.CTimeOutException e) {
                    e.printStackTrace();
                } finally {
                    boolLock.unLock();
                }
            },tName).start();
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boolLock.unLock();
        }

    }
}
