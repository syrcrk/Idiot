package ThreadDesignPattern.Chapter16;

public class ReadWriteClient {
    public static void main(String[] args) {
        final SharedData data=new SharedData(10);
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();

        new WriteWorker(data,"qwertiioewjkjkdfkweree").start();
        new WriteWorker(data,"qwertiioewjkjkdfkweree").start();
    }
}
