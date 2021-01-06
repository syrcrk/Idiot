package ThreadDesignPattern.Chapter16;

import java.util.Random;

public class WriteWorker extends Thread {

    public static final Random random = new Random(System.currentTimeMillis());
    private final SharedData data;
    private final String filler;
    private int index;

    public WriteWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true){
                char c=nextChar();
                data.write(c);
                System.out.println(Thread.currentThread().getName()+" write "+ c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index > filler.length()) {
            index = 0;
        }
        return c;
    }
}
