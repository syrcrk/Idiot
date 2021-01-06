package ThreadDesignPattern.Chapter22;

import java.util.LinkedList;

public class MessageQueue {
    private final LinkedList<Message> queue;
    private final static int MAX_COUNT = 100;
    private final int limit;

    public MessageQueue() {
        this(MAX_COUNT);
    }

    public MessageQueue(final int limit) {
        this.queue = new LinkedList<Message>();
        this.limit = limit;
    }

    public void put(Message msg) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > limit) {
                queue.wait();
            }
            queue.addLast(msg);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            Message msg = queue.removeFirst();
            queue.notifyAll();
            return msg;
        }
    }

    public int getQueueSize() {
        synchronized (queue){
            return queue.size();
        }
    }

    public int getLimit() {
        return limit;
    }
}
