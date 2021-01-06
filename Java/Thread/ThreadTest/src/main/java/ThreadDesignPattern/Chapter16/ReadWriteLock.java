package ThreadDesignPattern.Chapter16;

public class ReadWriteLock {
    public int readingReaders=0;
    public int waitingReaders=0;
    public int writingWriters=0;
    public int waitingWriters=0;
    boolean preferWriter=false;
    public ReadWriteLock(){
        this(true);
    }

    public ReadWriteLock(boolean preferWriter){
        this.preferWriter=preferWriter;
    }

    public synchronized void readLock()throws InterruptedException{
        this.waitingReaders++;
        try {
            while (writingWriters>0||(preferWriter&&waitingWriters>0)){
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.waitingReaders--;
        }
    }

    public synchronized  void readUnlock(){
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized  void writeLock()throws InterruptedException{
        this.waitingWriters++;
        try {
            while (readingReaders>0||writingWriters>0){
                this.wait();
            }
            this.writingWriters++;
        }finally {
            this.waitingWriters--;
        }
    }

    public synchronized  void writeUnlock(){
        this.writingWriters--;
        notifyAll();
    }
}
