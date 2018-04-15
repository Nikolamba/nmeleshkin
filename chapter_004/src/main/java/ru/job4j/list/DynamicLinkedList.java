package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> тип элементов в этом списке
 */
public class DynamicLinkedList<T> implements Iterable<T> {

    private Node<T> first = null;
    private Node<T> last = null;
    private int size = 0;
    private int modCount = 0;

    public void add(T value) {
        final Node<T> l = last;
        Node<T> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Node<T> n = findElementByIndex(index);
        return n.value;
    }

    public void removeFirst() {
        if (this.size == 1) {
            clear();
        } else {
            this.first = first.next;
            this.first.previous = null;
            size--;
            modCount++;
        }
    }

    public void removeLast() {
        if (this.size == 1) {
            clear();
        } else {
            this.last = last.previous;
            this.last.next = null;
            size--;
            modCount++;
        }

    }

    public void remove(int index) {
        Node<T> n = findElementByIndex(index);

        if (size == 1) {
            clear();
        } else if (n == last) {
            removeLast();
        } else if (n == first) {
            removeFirst();
        } else {
            Node<T> prev = n.previous;
            Node<T> next = n.next;
            prev.next = next;
            next.previous = prev;
            size--;
            modCount++;
        }

    }

    private void clear() {
        last = null;
        first = null;
        size = 0;
        modCount++;
    }

    private Node<T> findElementByIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> node = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return (node != null);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T result = node.value;
                node = node.next;
                return result;
            }
        };
    }

    private class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
