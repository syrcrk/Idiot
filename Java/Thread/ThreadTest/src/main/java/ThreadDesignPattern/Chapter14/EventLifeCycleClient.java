package ThreadDesignPattern.Chapter14;

import java.util.Arrays;

public class EventLifeCycleClient {
    public static void main(String[] args) {
        ThreadLifeCycleObserver observer=new ThreadLifeCycleObserver();
        observer.concurrentyQuery(Arrays.asList("1","2"));
    }
}
