package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <K> тип ключа для данной карты
 * @param <V> тип значений для данной карты
 */
public class SimpleHashMap<K, V> implements Iterable<K> {

    private final static int DEFAULT_INITIAL_CAPACITY = 4;

    private Node<K, V>[] table;
    private int size;
    private int modCount;
    private int capacity;

    @SuppressWarnings("unchecked")
    SimpleHashMap() {
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.table = (Node<K, V>[]) new Node[DEFAULT_INITIAL_CAPACITY];
    }

    public boolean insert(K key, V value) {

        boolean result;

        if (this.table.length <= this.size) {
            this.resize();
        }

        int hash = this.hash(key);
        if (this.table[hash] == null) {
            this.table[hash] = new Node<>(key, value);
            this.size++;
            this.modCount++;
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public V get(K key) {
        int hash = this.hash(key);
        return (this.table[hash] == null) ? null : this.table[hash].value;
    }

    public boolean delete(K key) {
        boolean result = false;
        int hash = this.hash(key);

        if (this.table[hash] != null) {
            this.table[hash] = null;
            size--;
            modCount++;
            result = true;
        }
        return result;
    }

    public int size() {
        return this.size;
    }

    private int hash(final Object o) {
        return (o == null) ? 0 : o.hashCode() % this.capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize() {

        this.capacity = this.capacity * 2;
        Node<K, V>[] copyTable = (Node<K, V>[]) new Node[this.capacity];

        for (Node<K, V> node : this.table) {
            if (node != null) {
                copyTable[this.hash(node.key)] = node;
            }
        }
        this.table = copyTable;
    }

    @Override
    public Iterator<K> iterator() {

        int saveModCount = this.modCount;

       return new Iterator<K>() {

            int currentSize = 0;
            int currentPosition = 0;

            @Override
            public boolean hasNext() {
                return (currentSize < size);
            }

            @Override
            public K next() {
                K result = null;
                if (saveModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int pos = currentPosition; pos < table.length; pos++) {
                    currentPosition++;
                    if (table[pos] != null) {
                        currentSize++;
                        result = table[pos].key;
                        break;
                    }
                }
                return result;
            }
        };
    }

    static class Node<K, V> {
        final K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }



}
