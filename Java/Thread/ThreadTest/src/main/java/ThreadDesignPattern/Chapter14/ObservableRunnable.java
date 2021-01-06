package ThreadDesignPattern.Chapter14;

public abstract class ObservableRunnable implements Runnable {
    private LifeCycleListener listener;

    public LifeCycleListener getListener() {
        return listener;
    }

    public void setListener(LifeCycleListener listener) {
        this.listener = listener;
    }

    public ObservableRunnable(final LifeCycleListener listener) {
        this.listener = listener;
    }

    public ObservableRunnable() {
    }

    public void notifyChange(final RunnableEvent event) {
        listener.onEvent(event);
    }

    public static enum RunnableState {
        RUNNING, ERROR, DONE
    }

    public static class RunnableEvent {
        public final RunnableState state;
        public final Thread thread;
        public final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
