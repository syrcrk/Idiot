package ThreadDesignPattern.Chapter14;

public class ObserverClient {
    public static void main(String[] args) {
        final Subject sub=new Subject();
        new BinaryObserver(sub);
        new OctObserver(sub);
        sub.setState(123);
    }
}
