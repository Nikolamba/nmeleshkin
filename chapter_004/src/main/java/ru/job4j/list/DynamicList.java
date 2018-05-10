package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> the type of elements in this list
 */
@ThreadSafe
public class DynamicList<T> implements Iterable<T> {

    @GuardedBy("this")
    private Object[] container;
    @GuardedBy("this")
    private int size;
    private static final int DEFAULT_CAPACITY = 2;
    @GuardedBy("this")
    private int modCount;

    public DynamicList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public synchronized void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @SuppressWarnings("unchecked")
    public synchronized T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) container[index];
    }

    public synchronized int size() {
        return this.size;
    }

    @Override
    public synchronized Iterator<T> iterator() {

        return new Iterator<T>() {

            private int position = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                synchronized (DynamicList.this) {
                    return (position < size);
                }
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                synchronized (DynamicList.this) {
                    if (expectedModCount != modCount) {
                        throw new ConcurrentModificationException();
                    }
                    return (T) container[position++];
                }
            }
        };
    }

    private synchronized void grow() {
        container = Arrays.copyOf(container, container.length * DEFAULT_CAPACITY);
    }
}