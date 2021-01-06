package ThreadDesignPattern.Chapter22;

import java.util.stream.IntStream;

public class TestClient {
    public static void main(String[] args) {
        MessageQueue queue=new MessageQueue();
        IntStream.range(0,3).forEach(i->{
            new Producer(queue,i).start();
            new Consumer(queue,i).start();
        });
    }
}
