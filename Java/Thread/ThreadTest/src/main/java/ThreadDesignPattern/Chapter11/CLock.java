package ThreadDesignPattern.Chapter11;

public interface CLock {
    public static class CTimeOutException extends Exception{
        public CTimeOutException(String msg){
            super(msg);
        }
    }

    void lock() throws InterruptedException;
    void lock(long mill) throws InterruptedException,CTimeOutException;
    void unLock() throws InterruptedException;
}
