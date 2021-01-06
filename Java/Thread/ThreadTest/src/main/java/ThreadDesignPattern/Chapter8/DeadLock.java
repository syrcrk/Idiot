package ThreadDesignPattern.Chapter8;

public class DeadLock {
    public final Object lock=new Object();

    public OhterService sercive;

    public DeadLock(OhterService mService){
        sercive=mService;
    }

    public void m1(){
        synchronized (lock){
            sercive.s1();
            System.out.println("m1********************");
        }
    }

    public void m2(){
        synchronized (lock){
            System.out.println("m2********************");
        }
    }
}
