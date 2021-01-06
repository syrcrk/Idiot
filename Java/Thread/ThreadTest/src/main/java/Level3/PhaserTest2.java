package Level3;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest2 {
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(6);
        for (int i = 0; i < 5; i++) {
            new A1(i, phaser).start();
        }
        new AHurt(5, phaser).start();
    }
    static class AHurt extends Thread {
        private final Phaser phaser;
        final int number;

        public AHurt(int no, Phaser phaser) {
            this.number = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                doSport(number, phaser, " start runing", " end runing");
                phaser.arriveAndDeregister();
//                doSport(number, phaser, " start bicycle", " end bicycle");
//                doSport(number, phaser, " start swim", " end swim");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
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
                doSport(number, phaser, " start runing", " end runing");

                doSport(number, phaser, " start bicycle", " end bicycle");

                doSport(number, phaser, " start swim", " end swim");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    private static void doSport(int number, Phaser phaser, String s, String s2) throws InterruptedException {
        System.out.println(number + s);
        TimeUnit.SECONDS.sleep(4);
        System.out.println(number + s2);
        phaser.arriveAndAwaitAdvance();
    }
}
