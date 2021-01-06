package ThreadDesignPattern.Chapter27;

import java.util.LinkedList;

public class ActivationQueue {
    private final static int MAX_COUNT=100;
    private final LinkedList<MethodRequest> methodRequest;

    public ActivationQueue() {
        this.methodRequest = new LinkedList<>();
    }
    public synchronized  void put(MethodRequest req){
        while(methodRequest.size()>MAX_COUNT){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        methodRequest.add(req);
        this.notifyAll();
    }

    public synchronized MethodRequest take(){
        while (methodRequest.size()<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest request= methodRequest.removeFirst();
        this.notifyAll();
        return request;
    }
}
