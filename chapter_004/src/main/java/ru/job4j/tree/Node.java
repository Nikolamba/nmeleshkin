package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Meleshkin(sol.of.f@mail.ru)
 * @version 0.1
 * @param <E> параметр типа для узла
 */
public class Node<E extends Comparable<E>> {

    private final List<Node<E>> children = new ArrayList<>();

    public E getValue() {
        return value;
    }

    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;

    }
}
