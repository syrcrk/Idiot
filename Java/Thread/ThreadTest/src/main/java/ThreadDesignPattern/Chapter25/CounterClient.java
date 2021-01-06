package ThreadDesignPattern.Chapter25;

public class CounterClient {
    public static void main(String[] args) {
        CounterIncrement counter=new CounterIncrement();
        counter.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        counter.close();
    }
}
