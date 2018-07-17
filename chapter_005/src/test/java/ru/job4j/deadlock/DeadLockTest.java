package ru.job4j.deadlock;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DeadLockTest {
    private Account acc1 = new Account(100);
    private Account acc2 = new Account(100);

    @Test
    public void whenTransferOneToTwoAndTwoToOneShouldGetDeadLock() throws InterruptedException {
        Thread fromTo = new Thread(() -> {
                try {
                    acc1.transfer(acc2, 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        Thread toFrom = new Thread(() -> {
                try {
                    acc2.transfer(acc1, 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        fromTo.start(); toFrom.start();
        fromTo.join(); toFrom.join();
    }

}