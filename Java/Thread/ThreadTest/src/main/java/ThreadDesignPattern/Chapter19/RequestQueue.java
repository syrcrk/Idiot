package ThreadDesignPattern.Chapter19;

import java.util.LinkedList;

public class RequestQueue {
    private final LinkedList<Request> queue=new LinkedList<>();

    public Request getRequest(){
        synchronized (queue){
            if(queue.size()<=0){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return queue.removeFirst();
    }

    public void putRequest(Request req){
        synchronized (queue){
            queue.addLast(req);
            queue.notifyAll();
        }
    }
}
