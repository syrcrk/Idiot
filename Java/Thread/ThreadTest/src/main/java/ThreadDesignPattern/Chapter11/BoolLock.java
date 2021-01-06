package ThreadDesignPattern.Chapter11;

import java.util.ArrayList;
import java.util.Collection;

public class BoolLock implements CLock {
    public boolean isLocked = false;
    Collection<Thread> threads = new ArrayList<>();
    Thread currentThread;

    @Override
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            threads.add(Thread.currentThread());
            this.wait();
        }
        isLocked = true;
        currentThread = Thread.currentThread();
        threads.remove(Thread.currentThread());
    }

    @Override
    public synchronized void lock(long mill) throws InterruptedException, CTimeOutException {
        if (mill < 0) {
            lock();
        } else {
            long endTime = System.currentTimeMillis() + mill;
            while (isLocked) {
                if (System.currentTimeMillis() > endTime){
                    throw new CTimeOutException("Timeout ");
                }
                threads.add(Thread.currentThread());
                this.wait(mill);
            }
            isLocked = true;
            currentThread = Thread.currentThread();
            threads.remove(Thread.currentThread());
        }
    }

    @Override
    public synchronized void unLock() {
        if (currentThread == Thread.currentThread()) {
            isLocked = false;
            this.notifyAll();
        }
    }
}
