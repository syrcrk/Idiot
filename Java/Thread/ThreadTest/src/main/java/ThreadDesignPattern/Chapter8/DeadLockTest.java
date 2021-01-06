package ThreadDesignPattern.Chapter8;

public class DeadLockTest {
    public static final void main(String[] args){
        final OhterService os=new OhterService();
        final DeadLock dl=new DeadLock(os);
        os.setDeadLock(dl);


        new Thread(new Runnable() {
            public void run() {
                while(true){
                    dl.m1();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    os.s2();
                }
            }
        }).start();
    }
}
