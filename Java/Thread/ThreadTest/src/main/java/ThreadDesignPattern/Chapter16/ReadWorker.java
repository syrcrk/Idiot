package ThreadDesignPattern.Chapter16;

public class ReadWorker extends Thread {
    private final SharedData data;

    public ReadWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run(){
        try {
            while (true){
                char[] readBuffer=data.read();
                System.out.println(Thread.currentThread().getName()+" read "+ toString().valueOf(readBuffer));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
