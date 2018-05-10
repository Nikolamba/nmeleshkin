package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@ThreadSafe
public class Lock {
    @GuardedBy("this")
    private Thread acquiredThread = null;
    @GuardedBy("this")
    private Boolean locked = false;

    public synchronized void lock() {
        while (locked && this.acquiredThread != Thread.currentThread()) {
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        this.locked = true;
        this.acquiredThread = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (this.acquiredThread != Thread.currentThread()) {
            throw new UnsupportedOperationException();
        }
        this.acquiredThread = null;
        this.locked = false;
        notify();
    }
}
