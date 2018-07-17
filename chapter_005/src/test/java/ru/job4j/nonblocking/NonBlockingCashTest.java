package ru.job4j.nonblocking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NonBlockingCashTest {

    private NonBlockingCash cash = new NonBlockingCash();

    @Before
    public void before() {
        this.cash.add(new Model(1, "name_1"));
    }

    @Test
    public void whenTwoThreadsUpdateData() throws InterruptedException {
        Thread thread1 = new Thread(() -> changeModel());
        Thread thread2 = new Thread(() -> changeModel());

        thread1.start(); thread2.start();
        thread1.join(); thread2.join();

    }

    private void changeModel() {
        for (int i = 0; i < 10; i++) {
            int version = cash.getModelById(1).getVersion();
            Model newModel = new Model(1, "" + Thread.currentThread().getName());
            newModel.setVersion(version);
            cash.update(newModel);
        }
    }

}