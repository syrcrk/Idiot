package ThreadDesignPattern.Chapter24;

import ThreadDesignPattern.Chapter22.Message;

import java.util.stream.IntStream;

public class ThreadClient {
    public static void main(String[] args) {
        final MessageHandler hand=new MessageHandler();
        IntStream.range(0,10).forEach(i-> hand.request(new Message(String.valueOf(i)+"dddd")));
        hand.shutDown();
    }
}
