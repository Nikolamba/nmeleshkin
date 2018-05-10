package ru.job4j.wait;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class LockTest {

    private Lock lock = new Lock();

    @Test
    public void whenThreadsLockCommonObject() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                work();
            }
        });

        Thread thread2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                work();
            }
        });

        Thread thread3 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                work();
            }
        });

        thread1.start(); thread2.start(); thread3.start();
        thread1.join(); thread2.join(); thread3.join();
    }

    private void work() {
        System.out.println(Thread.currentThread().getName() + " waiting lock" + "\n");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " equired lock and go to sleep" + "\n");
        try {
            Thread.sleep(3000);
            System.out.println("Thread " + Thread.currentThread().getName() + " awoke");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        lock.unlock();
    }
}