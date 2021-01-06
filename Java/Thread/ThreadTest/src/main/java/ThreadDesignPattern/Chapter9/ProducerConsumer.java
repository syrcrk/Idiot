package ThreadDesignPattern.Chapter9;

public class ProducerConsumer {
    public final Object lock=new Object();
    volatile boolean isProduced=false;
    int i=0;
    public void Produce(){
        synchronized (lock){
            if(isProduced){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }else{
                i++;
                System.out.println("Produce "+i);
                lock.notify();
                isProduced=true;
            }
        }
    }


    public void Consume(){
        synchronized (lock){
            if(isProduced){
                System.out.println("Consume "+i);
                isProduced=false;
                lock.notify();
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }
    }

    public static final void main(String[] args){
        final ProducerConsumer pc=new ProducerConsumer();
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    pc.Produce();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    pc.Consume();
                }
            }
        }).start();
    }
}
