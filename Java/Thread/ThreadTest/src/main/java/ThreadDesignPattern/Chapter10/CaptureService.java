package ThreadDesignPattern.Chapter10;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CaptureService {
    public static final void main(String[] args){
        List<Thread> workerList=new LinkedList<Thread>();
        Arrays.asList("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10","M11","M12","M13","M14").stream()
                .map(CaptureService::createCaptureThread).forEach(t->{
                    t.start();
                    workerList.add(t);
                }
        );
        workerList.stream().forEach(t->{
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
    public static LinkedList<Control> CONTROLERS =new LinkedList<Control>();
    public static Thread createCaptureThread(String name){
        return new Thread(()->{
            Optional.of("The work thread "+Thread.currentThread().getName()+" begin capture data").ifPresent(System.out::println);
            synchronized (CONTROLERS){
                while(CONTROLERS.size()>5){
                    try {
                        CONTROLERS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLERS.addLast(new Control());
            }
            Optional.of("worker "+Thread.currentThread().getName()+" isworking").ifPresent(System.out::println);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLERS){
                Optional.of("The work thread "+Thread.currentThread().getName()+" end capture data").ifPresent(System.out::println);
                CONTROLERS.removeFirst();
                CONTROLERS.notifyAll();
            }
        },name);
    }

}
