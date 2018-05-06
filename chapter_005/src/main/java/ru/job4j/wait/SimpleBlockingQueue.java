package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> тип данных для данной структуры
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private static final int MAX_COUNT = 10;

    public synchronized void offer(T value) {
        while (this.queue.size() >= MAX_COUNT) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        this.queue.offer(value);
        System.out.println("Added value: " + value + ", size = " + this.queue.size());
        this.notifyAll();
    }

    public synchronized T poll() {
        while (this.queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        T result = this.queue.poll();
        System.out.println("Extract value: " + result + ", size = " + this.queue.size());
        this.notifyAll();
        return result;
    }
}
