package ThreadDesignPattern.Chapter26;

import java.util.Arrays;

public class Channel {
    private final static int MAX_COUNT = 100;

    private final Request[] requestQueue;

    private int head;
    private int tail;
    private int count;

    private final WorkerThread[] workerPool;

    public Channel(int worker) {
        this.count = worker;
        this.requestQueue = new Request[MAX_COUNT];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[worker];
        this.init();
    }

    void init() {
        for (int i = 0; i < workerPool.length; i++) {
            this.workerPool[i] = new WorkerThread("work " + i, this);
        }
    }

    public void startWorker() {
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    public synchronized void  put(Request req){
        while (count>requestQueue.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.requestQueue[tail]=req;
        tail=(tail+1)%requestQueue.length;
        this.count++;
        this.notifyAll();
    }
    public synchronized  Request take(){
        while (count<=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request res=this.requestQueue[head];
        head=(head+1)%requestQueue.length;
        count--;
        this.notifyAll();
        return res;
    }
}
