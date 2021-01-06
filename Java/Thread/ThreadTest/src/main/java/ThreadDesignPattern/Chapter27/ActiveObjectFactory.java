package ThreadDesignPattern.Chapter27;

public final class ActiveObjectFactory {
    private ActiveObjectFactory(){
    }

    public static ActiveObject createActiveObject(){
        Servant servant=new Servant();
        ActivationQueue queue=new ActivationQueue();
        SchedulerThread scheduler=new SchedulerThread(queue);
        ActiveObjectProxy proxy=new ActiveObjectProxy(scheduler,servant);
        scheduler.start();
        return proxy;
    }
}
