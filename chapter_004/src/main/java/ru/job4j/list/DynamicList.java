package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> the type of elements in this list
 */
public class DynamicList<T> implements Iterable<T> {

    private Object[] container;
    private int size;
    private static final int DEFAULT_CAPACITY = 2;
    private int modCount;

    public DynamicList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int position = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return (position < size);
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[position++];
            }
        };
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length * DEFAULT_CAPACITY);
    }
}