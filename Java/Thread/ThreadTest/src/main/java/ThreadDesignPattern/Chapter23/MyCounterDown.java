package ThreadDesignPattern.Chapter23;

public class MyCounterDown {
    private final int total;
    int count;
    public MyCounterDown(int count) {
        total=count;
        count=0;
    }

    public synchronized void countDown(){
        count++;
        this.notifyAll();
    }

    public synchronized void await() throws InterruptedException{
        while (count!=total){
            this.wait();
        }
    }
}
