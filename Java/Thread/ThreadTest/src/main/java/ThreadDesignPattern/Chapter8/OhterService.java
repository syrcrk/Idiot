package ThreadDesignPattern.Chapter8;

public class OhterService {
    public final Object lock=new Object();
    public DeadLock deadLock;
    public void s1(){
        synchronized (lock){
            System.out.println("s1********************");
        }
    }

    public void s2(){
        synchronized (lock){
            System.out.println("s2********************");
            deadLock.m2();
        }
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
