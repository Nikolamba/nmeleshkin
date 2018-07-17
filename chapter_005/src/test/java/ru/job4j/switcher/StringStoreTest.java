package ru.job4j.switcher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StringStoreTest {

    private final StringStore store = new StringStore();
    private boolean checking = true;

    @Test
    public void whenHaveTwoThreadsShouldCorrectResult() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (store) {
                while (checking || !Thread.currentThread().isInterrupted()) {
                    action(1, false);
                    try {
                        store.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (store) {
                    while (!checking) {
                        action(2, true);
                        store.notify();
                    }
                }
            }
        });
        thread1.start(); thread2.start();
        thread1.join(); thread2.join();
    }

    private void action(int addedValue, boolean flag) {
        this.checking = flag;
        for (int i = 0; i < 10; i++) {
            store.addValue(addedValue);
        }
        System.out.println(store.toString());
    }
}