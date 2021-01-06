package ThreadDesignPattern.Chapter18;

public interface Future<T> {
    T get() throws  InterruptedException;
}
