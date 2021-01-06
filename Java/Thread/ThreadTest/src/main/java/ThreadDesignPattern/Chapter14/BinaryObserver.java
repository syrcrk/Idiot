package ThreadDesignPattern.Chapter14;

public class BinaryObserver extends Observer {
    public BinaryObserver(Subject sub){
        super(sub);
    }
    @Override
    public void update() {
        System.out.println("binary update "+Integer.toBinaryString(subject.getState()));
    }
}
