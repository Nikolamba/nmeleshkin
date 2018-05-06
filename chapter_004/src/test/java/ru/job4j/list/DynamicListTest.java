package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */

public class DynamicListTest {

    private final DynamicList<Integer> dynamicList = new DynamicList<>();

    class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                dynamicList.add(1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenAddFiveElements() {
        dynamicList.add(1);
        dynamicList.add(2);
        dynamicList.add(3);
        dynamicList.add(4);
        dynamicList.add(5);
        assertThat(dynamicList.get(0), is(1));
        assertThat(dynamicList.get(4), is(5));
    }

    @Test
    public void whenUseGet() {
        dynamicList.add(1);
        dynamicList.add(2);
        dynamicList.add(3);
        assertThat(dynamicList.get(0), is(1));
        assertThat(dynamicList.get(2), is(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUseIteratorShouldNoSuchElementException() {
        dynamicList.add(1);
        dynamicList.add(2);
        dynamicList.add(3);
        Iterator<Integer> iterator = dynamicList.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenUserIteratorShouldConcurentException() {
        dynamicList.add(1);
        Iterator<Integer> iterator = dynamicList.iterator();
        dynamicList.add(2);
        iterator.next();
    }

    @Test
    public void whenSeveralThreadsAddElementsShouldAllElementsAdded() throws InterruptedException {
       Thread thread1 = new Thread(new AddThread());
       Thread thread2 = new Thread(new AddThread());
       Thread thread3 = new Thread(new AddThread());
       thread1.start(); thread2.start(); thread3.start();
       thread1.join(); thread2.join(); thread3.join();
       assertThat(dynamicList.size(), is(15));
    }

    @Test
    public void whenSeveralThreadsAddElementsAndUseIterator() throws InterruptedException {
        dynamicList.add(1);
        dynamicList.add(2);
        dynamicList.add(3);
        dynamicList.add(4);
        dynamicList.add(5);

        Thread iteratorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dynamicList) {
                    Iterator<Integer> iterator = dynamicList.iterator();

                    for (; iterator.hasNext();) {
                        System.out.println(String.format("%s, %s", "Iterator = ", iterator.next()));
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread addThread1 = new Thread(new AddThread());
        Thread addThread2 = new Thread(new AddThread());
        addThread1.start();
        iteratorThread.start();
        addThread2.start();

        addThread1.join(); addThread2.join(); iteratorThread.join();
    }
}