package ThreadDesignPattern.Chapter21;

import java.util.stream.IntStream;

public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(0,10).forEach((i)->{
            new Thread(new ExecutionTask()).start();
        });
    }
}
