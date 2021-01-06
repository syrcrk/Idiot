package ThreadDesignPattern.Chapter26;

import java.util.stream.IntStream;

public class WorkerClient {
    public static void main(String[] args) {
        final Channel channel=new Channel(5);
        channel.startWorker();

        IntStream.range(0,4).forEach(i->{
            new TransportThread("transport "+i,channel).start();
        });
    }
}
