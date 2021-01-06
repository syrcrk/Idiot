package ThreadDesignPattern.Chapter6;

public class ThreadServiceMain {
    public static final void main(String[] args){
        ThreadService service=new ThreadService();
        long time=System.currentTimeMillis();
        service.execute(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Job finished");
            }
        });
        service.shutDown(10000);
        System.out.println(System.currentTimeMillis()-time);
    }
}
