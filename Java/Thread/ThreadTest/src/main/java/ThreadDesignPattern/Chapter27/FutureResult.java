package ThreadDesignPattern.Chapter27;

public class FutureResult implements  Result {
    private Result result;
    private boolean isReady;

    public  synchronized  void setResult(Result result){
        this.result=result;
        this.isReady=true;
        this.notifyAll();
    }

    @Override
    public synchronized Object getResultValue() {
        while (!isReady){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.result.getResultValue();
    }
}
