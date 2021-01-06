package ThreadDesignPattern.Chapter15;

public class Client {
    public static void main(String[] args) {
        Gate gate=new Gate();
        User user1=new User("BB","BeiJing",gate);
        User user2=new User("SS","Shanghai",gate);
        User user3=new User("GG","GuanZhou",gate);

        user1.start();
        user2.start();
        user3.start();
    }
}
