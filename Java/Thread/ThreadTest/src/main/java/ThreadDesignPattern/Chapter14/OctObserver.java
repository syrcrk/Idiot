package ThreadDesignPattern.Chapter14;

public class OctObserver extends Observer {
    public OctObserver(Subject sub){
        super(sub);
    }
    @Override
    public void update() {
        System.out.println("oct "+ Integer.toOctalString(subject.getState()));
    }
}
