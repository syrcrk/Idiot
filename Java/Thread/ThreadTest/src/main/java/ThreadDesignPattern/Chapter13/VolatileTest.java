package ThreadDesignPattern.Chapter13;

public class VolatileTest {
    public volatile static int INIT_VALUE=4;
    public static void main(String[] args) {
        new Thread(()->{
            int v=INIT_VALUE;
            while(v<10){
                if(v!=INIT_VALUE){
                    v=INIT_VALUE;
                    System.out.println("Update Value to "+v);
                }
            }
        }).start();

        new Thread(()->{
            int v=INIT_VALUE;
            while(v<10) {
                v++;
                INIT_VALUE=v;
                System.out.println("Set Value to "+v);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
