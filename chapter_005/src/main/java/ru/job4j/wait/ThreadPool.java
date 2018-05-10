package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@ThreadSafe
public class ThreadPool {

    @GuardedBy("itself")
    private final Queue<Work> tasks;
    private final Thread[] threads;
    private final int numThreads;

    ThreadPool(int numThreads) {
        tasks = new LinkedList<>();
        threads = new Thread[numThreads];
        this.numThreads = numThreads;
    }

    public void startThreads() {
        for (int i = 0; i < this.numThreads; i++) {
            threads[i] = new ThreadTask();
            threads[i].start();
        }
    }

    public Thread getThread(int id) {
        return this.threads[id];
    }

    public void add(Work work) {
        synchronized (tasks) {
            tasks.add(work);
            tasks.notifyAll();
        }
    }

    private class ThreadTask extends Thread {
        @Override
        public void run() {
            Work work;
            System.out.println("Запущен поток: " + Thread.currentThread().getId());

            while (!Thread.currentThread().isInterrupted()) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    work = tasks.poll();
                }
                System.out.println("Task start. Thread " + Thread.currentThread().getId());
                work.execute();
                System.out.println("Task finish. Thread " + Thread.currentThread().getId());
            }
        }
    }
}


