package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> тип элементов в этом списке
 */
public class SimpleArray<T> implements Iterable<T> {

    private int capacity;
    private Object[] elements;
    private int size;

    SimpleArray() {
        this.capacity = 3;
        this.elements = new Object[this.capacity];
        this.size = 0;
    }

    public void add(T model) {
        this.elements[size++] = model;
        this.checkSize();
    }

    public void set(int index, T model) {
        this.elements[index] = model;
    }

    public void delete(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(this.elements, index + 1, this.elements, index, this.size - index + 1);
        this.size--;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) this.elements[index];
    }

    public int getIndex(T model) {
        int result = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(model)) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int position = 0;

            @Override
            public boolean hasNext() {
                return (position < size);
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (this.position >= size) {
                    throw new NoSuchElementException();
                }
                return (T) elements[position++];
            }
        };
    }

    //если нужно увеличиваем объем массива
    private void checkSize() {
        if (this.size >= this.capacity - 1) {
            this.capacity += this.capacity;
            this.elements = Arrays.copyOf(this.elements, this.capacity);
        }
    }
}
