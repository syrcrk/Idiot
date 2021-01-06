package ThreadDesignPattern.Chapter6;

public class ThreadService {
    boolean isFinished=false;
    Thread th;
    public void execute(final Runnable run){
        th=new Thread(new Runnable() {
            public void run() {
                isFinished=false;
                Thread inner=new Thread(run);
                inner.setDaemon(true);
                inner.start();
                try {
                    inner.join();
                    isFinished=true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        });
        th.start();
    }


    public void shutDown(long millise){
        long time=System.currentTimeMillis();
        while (!isFinished){
            if(System.currentTimeMillis()-time>=millise){
                System.out.println("Interrupt when time out");
                th.interrupt();
                break;
            }
            try {
                th.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
            System.out.println("not finished");
        }
        if(isFinished){
            System.out.println("task finished gracefully");
        }

    }
}
