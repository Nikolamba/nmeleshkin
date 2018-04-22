package ru.job4j.tree;

import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <E> тип данных для данной структуры
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    private int size;
    private int modCount;

    Tree(E root) {
        this.root = new Node<>(root);
        size++;
        modCount++;
    }

    public boolean add(E parent, E child) {
        boolean result = false;
        Queue<Node<E>> data = this.createQueue();
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.getValue().compareTo(parent) == 0) {
                el.leaves().add(new Node<>(child));
                size++;
                modCount++;
                result = true;
                break;
            }
        }
        return result;
    }

    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = this.createQueue();
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
        }
        return rsl;
    }

    public boolean isBynary() {
        boolean result = true;
        Queue<Node<E>> data = this.createQueue();
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    private Queue<Node<E>> createQueue() {
        List<Node<E>> data = new LinkedList<>();
        Queue<Node<E>> queue = new LinkedList<>();

        data.add(root);
        for (int i = 0; i < size; i++) {
            data.addAll(data.get(i).leaves());
        }
        queue.addAll(data);
        return queue;
    }

    @Override
    public Iterator<E> iterator() {
        Queue<Node<E>> queue = this.createQueue();
        return new Iterator<E>() {
            int saveModeCount = modCount;

            @Override
            public boolean hasNext() {
                return (!queue.isEmpty());
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (saveModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return queue.poll().getValue();
            }
        };
    }
}
