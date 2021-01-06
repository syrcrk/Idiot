package ThreadDesignPattern.Chapter18;

public class AsynFuture<T> implements Future<T> {
    private volatile  boolean done=false;
    private T result;
    @Override
    public T get() throws InterruptedException {
        synchronized (this){
            while (!done){
                this.wait();
            }
        }
        return result;
    }

    public void done(T result){
        synchronized (this){
            this.result=result;
            done=true;
            this.notifyAll();
        }
    }
}
