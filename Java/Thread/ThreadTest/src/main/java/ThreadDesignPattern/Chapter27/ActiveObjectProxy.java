package ThreadDesignPattern.Chapter27;

class ActiveObjectProxy implements ActiveObject {
    private final SchedulerThread schedulThread;
    private final Servant servant;
    public ActiveObjectProxy(SchedulerThread thread, Servant servant) {
        this.schedulThread = thread;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char filChar) {
        FutureResult futureResult=new FutureResult();
        schedulThread.invoke(new MakeStringRequest(servant,futureResult,count,filChar));
        return futureResult;
    }

    @Override
    public void display(String str) {
        schedulThread.invoke(new DisplayStringRequest(servant,null,str));
    }
}
