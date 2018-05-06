package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> тип элементов в этом списке
 */
@ThreadSafe
public class DynamicLinkedList<T> implements Iterable<T> {

    @GuardedBy("this")
    private Node<T> first = null;
    @GuardedBy("this")
    private Node<T> last = null;
    @GuardedBy("this")
    private int size = 0;
    @GuardedBy("this")
    private int modCount = 0;

    public synchronized void add(T value) {

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

    public synchronized void removeFirst() {
        if (this.size == 1) {
            clear();
        } else {
            this.first = first.next;
            this.first.previous = null;
            size--;
            modCount++;
        }
    }

    public synchronized void removeLast() {
        if (this.size == 1) {
            clear();
        } else {
            this.last = last.previous;
            this.last.next = null;
            size--;
            modCount++;
        }
    }

    public synchronized void remove(int index) {
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

    private synchronized void clear() {
        last = null;
        first = null;
        size = 0;
        modCount++;
    }

    private synchronized Node<T> findElementByIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    public synchronized int size() {
        return this.size;
    }

    @Override
    public synchronized Iterator<T> iterator() {
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
