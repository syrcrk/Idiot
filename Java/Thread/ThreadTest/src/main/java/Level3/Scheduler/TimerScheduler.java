package Level3.Scheduler;

import java.util.Timer;
import java.util.TimerTask;

public class TimerScheduler {
    public static void main(String[] args) {
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" exe ");
            }
        };
        timer.scheduleAtFixedRate(task,1,1000);

    }
}
