package ThreadDesignPattern.Chapter7;

public class SyncTest {
    public static final Object obj=new Object();
    public static final void main(String[] args){
        Runnable run=new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start ");
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName()+" get lock ");
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        };
        Thread th=new Thread(run);
        Thread th1=new Thread(run);
        Thread th2=new Thread(run);

        th.start();
        th1.start();
        th2.start();

    }
}
