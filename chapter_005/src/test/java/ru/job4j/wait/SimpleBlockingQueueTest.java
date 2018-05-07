package ru.job4j.wait;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class SimpleBlockingQueueTest {

    private SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

    class PollValue implements Runnable {
        private int repeatCount;

        PollValue(int repeatCount) {
            this.repeatCount = repeatCount;
        }
        @Override
        public void run() {
            for (int i = 0; i < this.repeatCount; i++) {
                queue.poll();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    class OfferValue implements Runnable {
        private int repeatCount;

        OfferValue(int repeatCount) {
            this.repeatCount = repeatCount;
        }
        @Override
        public void run() {
            for (int i = 0; i < this.repeatCount; i++) {
                queue.offer(i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenWorkingSeveralThreads() throws InterruptedException {
        Thread pollThread1 = new Thread(new PollValue(10));
        Thread pollThread2 = new Thread(new PollValue(10));
        Thread offerThread1 = new Thread(new OfferValue(20));
        pollThread1.start(); offerThread1.start(); pollThread2.start();
        pollThread1.join(); offerThread1.join(); pollThread2.join();
    }

    @Test
    public void whenTwoThreadsOfferAndOneThreadPoll() throws InterruptedException {
        Thread offerThread1 = new Thread(new OfferValue(10));
        Thread offerThread2 = new Thread(new OfferValue(10));
        Thread offerThread3 = new Thread(new OfferValue(10));
        Thread pollThread = new Thread(new PollValue(30));
        offerThread1.start(); offerThread2.start(); pollThread.start(); offerThread3.start();
        offerThread1.join(); offerThread2.join(); pollThread.join(); offerThread3.join();
    }

}