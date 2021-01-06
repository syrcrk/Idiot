package ThreadDesignPattern.Chapter12;

public class Hook {
    public static final void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("Client has an error will exit");

        }));
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i > 4) {
                throw new RuntimeException("xxxx");
            }
            System.out.println("I working ");
        }
    }
}
