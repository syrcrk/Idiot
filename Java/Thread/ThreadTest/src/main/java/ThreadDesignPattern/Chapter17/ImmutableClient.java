package ThreadDesignPattern.Chapter17;

import java.util.stream.IntStream;

public class ImmutableClient {
    public static void main(String[] args) {
        Person person=new Person("Jons","street 1");
        IntStream.range(0,10).forEach(i->new UserPersonThread(person).start());
    }
}
