package ThreadDesignPattern.Chapter20;

import java.util.Optional;

public class ThreadLocalTest {
    static final ThreadLocal<String> threadLocal=new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };
    public static void main(String[] args) {
        threadLocal.set("gif");
        new Thread(()->{
            Optional.of(threadLocal.get()).ifPresent(System.out::println);
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadLocal.get());
    }
}
