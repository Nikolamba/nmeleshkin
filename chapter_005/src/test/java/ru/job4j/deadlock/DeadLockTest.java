package ru.job4j.deadlock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DeadLockTest {
    private ReentrantLock lockFrom = new ReentrantLock(true);
    private ReentrantLock lockTo = new ReentrantLock(true);
    private final CountDownLatch start = new CountDownLatch(2);

    @Test
    public void whenTransferOneToTwoAndTwoToOneShouldGetDeadLock() throws InterruptedException {
        Thread fromTo = new Thread(() -> {
            this.action();
        });
        Thread toFrom = new Thread(() -> {
            this.action();
        });

        fromTo.start(); toFrom.start();
        fromTo.join(); toFrom.join();
    }

    private void action() {
        try {
            start.await();
            start.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lockTo.lock();
        lockFrom.lock();
        lockFrom.unlock();
        lockTo.unlock();
    }

}