package Level3;

import java.util.concurrent.Phaser;

public class PhaserTest4 {
    public static void main(String[] args) {
        final Phaser phaser=new Phaser(1);
        System.out.println(phaser.getPhase());
        phaser.arriveAndAwaitAdvance();

        System.out.println(phaser.getPhase());
        phaser.arriveAndAwaitAdvance();

        System.out.println(phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
    }
}
