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
public class DynamicLinkedListTest {

    private final DynamicLinkedList<Integer> list = new DynamicLinkedList<>();

    class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                list.add(i);
                System.out.println(String.format("%s %s %s %s", "Поток: ",
                        Thread.currentThread().getName(), "добавил ", i));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    class DeleteLastThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                list.removeLast();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    class DeleteByIndexThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (list) {
                    list.remove(list.size() - 1);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenAddElements() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.size(), is(3));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(4));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUseIterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
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
    public void whenAddElementShouldConcurentException() {
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        list.add(2);
        iterator.next();
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUseIteratorForEmptyListShouldNoSuchElementException() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test
    public void whenSeveralThreadsAddElements() throws InterruptedException {
        Thread addThread1 = new Thread(new AddThread());
        Thread addThread2 = new Thread(new AddThread());
        Thread addThread3 = new Thread(new AddThread());
        addThread1.start();
        addThread2.start();
        addThread3.start();
        addThread1.join(); addThread2.join(); addThread3.join();
        assertThat(list.size(), is(15));
    }

    @Test
    public void whenSeveralThreadsDeleteLastElement() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        Thread delLast1 = new Thread(new DeleteLastThread());
        Thread delLast2 = new Thread(new DeleteLastThread());
        Thread delByIndex1 = new Thread(new DeleteByIndexThread());
        Thread delByIndex2 = new Thread(new DeleteByIndexThread());
        delLast1.start(); delLast2.start();
        delByIndex1.start(); delByIndex2.start();
        delLast1.join(); delLast2.join();
        delByIndex1.join(); delByIndex2.join();
        assertThat(list.size(), is(0));
    }

    @Test
    public void whenSeveralThreadsAddElementsAndUseIterator() throws InterruptedException {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Thread iteratorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    Iterator<Integer> iterator = list.iterator();
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