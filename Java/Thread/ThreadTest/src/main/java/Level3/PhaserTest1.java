package Level3;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest1 {
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for(int i=0;i<5;i++){
            new A1(i,phaser).start();
        }
    }

    static class A1 extends Thread {
        private final Phaser phaser;
        final int number;

        public A1(int no, Phaser phaser) {
            this.number = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.number + " start runing");
                TimeUnit.SECONDS.sleep(4);
                System.out.println(this.number + " end runing");
                this.phaser.arriveAndAwaitAdvance();

                System.out.println(this.number + " start bicycle");
                TimeUnit.SECONDS.sleep(4);
                System.out.println(this.number + " end bicycle");
                this.phaser.arriveAndAwaitAdvance();

                System.out.println(this.number + " start swim");
                TimeUnit.SECONDS.sleep(4);
                System.out.println(this.number + " end swim");
                this.phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }


        }
    }
}
