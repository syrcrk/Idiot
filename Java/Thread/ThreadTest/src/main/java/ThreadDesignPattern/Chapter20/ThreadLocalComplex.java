package ThreadDesignPattern.Chapter20;

import java.util.Random;

public class ThreadLocalComplex {
    static final ThreadLocal<String> threadLocal=new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };
    static Random random=new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Thread t1=new Thread(()->{
           threadLocal.set("T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2=new Thread(()->{
            threadLocal.set("T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
    }
}
