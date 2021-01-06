package ThreadDesignPattern.Chapter14;


import java.util.List;

public class ThreadLifeCycleObserver implements LifeCycleListener {
    public static final Object LOCK = new Object();

    public void concurrentyQuery(List<String> ids) {
        if (ids == null || !ids.isEmpty()) {
            ids.stream().forEach(id -> {
                new Thread(
                        new ObservableRunnable(this) {
                            @Override
                            public void run() {
                                try {
                                    notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                                    System.out.println("query for id "+id);
                                    notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
                                } catch (Exception e){
                                    notifyChange(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),null));
                                }
                            }
                        }
                        , id).start();
            });

        }
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println(event.state+" "+event.thread.getName());
            if(event.getCause()!=null){
                System.out.println("Thrad runnable "+event.thread.getName()+" failed");
            }
        }
    }
}
