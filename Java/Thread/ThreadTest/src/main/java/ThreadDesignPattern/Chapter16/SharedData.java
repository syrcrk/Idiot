package ThreadDesignPattern.Chapter16;

public class SharedData {
    private  final char[] buffer;
    private final ReadWriteLock lock=new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        for(int i=0;i<size;i++){
            buffer[i]='*';
        }
    }

    public char[] read() throws InterruptedException{
        try {
            lock.readLock();
            return doRead();
        }  finally {
            lock.readUnlock();
        }
    }

    public char[] doRead(){
        char[] newBuffer=new char[buffer.length];
        for(int i=0;i<buffer.length;i++){
            newBuffer[i]=buffer[i];
        }
        slow(50);
        return newBuffer;
    }
    void slow(long mil){
        try {
            Thread.sleep(mil);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(char c)throws InterruptedException{
        try {
            lock.writeLock();
            this.doWrite(c);
            slow(50);
        } finally {
            lock.writeUnlock();
        }
    }

    public void doWrite(char c){
        for(int i=0;i<buffer.length;i++){
            buffer[i]=c;
        }
    }
}
