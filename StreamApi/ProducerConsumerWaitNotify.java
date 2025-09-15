package DSA.StreamApi;

import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private final int capacity = 5;
    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // wait if buffer is full
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        notifyAll(); // wake up consumers
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // wait if buffer is empty
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item);
        notifyAll(); // wake up producers
        return item;
    }
}

public class ProducerConsumerWaitNotify {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            int item = 1;
            try {
                while (true) {
                    buffer.produce(item++);
                    Thread.sleep(500); // simulate time to produce
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    buffer.consume();
                    Thread.sleep(1000); // simulate time to consume
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        producer.start();
        consumer.start();
    }
}
