package ru.job4j.tree;

import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> тип данных для данной структуры
 */
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root;
    private int size;
    private int modCount;

    BinarySearchTree(T value) {
        this.root = new Node<>(null, null, null, value);
        this.size = 1;
    }

    public void add(T value) {
        this.addTo(this.root, value);
    }

    private void addTo(Node<T> node, T value) {

        if (node.value.compareTo(value) >= 0) {
            if (node.leftTree == null) {
                node.leftTree = new Node<>(node, null, null, value);
                this.size++;
                this.modCount++;
            } else {
                addTo(node.leftTree, value);
            }
        } else {
            if (node.rightTree == null) {
                node.rightTree = new Node<>(node, null, null, value);
                this.size++;
                this.modCount++;
            } else {
                addTo(node.rightTree, value);
            }
        }

    }

    public int getSize() {
        return this.size;
    }

    public Node<T> getRoot() {
        return root;
    }

    private Queue<Node<T>> createQueue() {
        List<Node<T>> data = new LinkedList<>();
        Queue<Node<T>> queue = new LinkedList<>();

        data.add(root);
        for (int i = 0; i < size; i++) {
            if (data.get(i).leftTree != null) {
                data.add(data.get(i).leftTree);
            }
            if (data.get(i).rightTree != null) {
                data.add(data.get(i).rightTree);
            }
        }
        queue.addAll(data);
        return queue;
    }

    @Override
    public Iterator<T> iterator() {
        int saveModCount = this.modCount;
        Queue<Node<T>> queue = this.createQueue();

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return (!queue.isEmpty());
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (saveModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return queue.poll().value;
            }
        };
    }

    static class Node<E extends Comparable<E>> {

        Node<E> parent;
        Node<E> rightTree;
        Node<E> leftTree;
        E value;

        Node(Node<E> parent, Node<E> rightTree, Node<E> leftTree, E value) {

            this.parent = parent;
            this.rightTree = rightTree;
            this.leftTree = leftTree;
            this.value = value;
        }
    }
}
