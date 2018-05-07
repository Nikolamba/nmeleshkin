package ru.job4j.wait;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ThreadPoolTest {

    @Test
    public void whenRunningSeveralThreads() throws InterruptedException {
        int n = Runtime.getRuntime().availableProcessors();
        ThreadPool pool = new ThreadPool(n);
        for (int i = 0; i < 20; i++) {
            pool.add(new Work());
            Thread.sleep(100);
        }
        pool.getThreads(0).join();
    }

}