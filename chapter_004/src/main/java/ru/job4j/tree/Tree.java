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
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.getValue().compareTo(parent) == 0) {
                el.leaves().add(new Node<>(child));
                size++;
                modCount++;
                result = true;
                break;
            }
            for (Node<E> node : el.leaves()) {
                data.offer(node);
            }
        }
        return result;
    }

    public Optional<Node<E>> findBy(E value) {

        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }


    @Override
    public Iterator<E> iterator() {
        //создаем список всех элементов дерева
        List<Node<E>> data = new LinkedList<>();
        data.add(root);
        for (int i = 0; i < size; i++) {
            Node<E> el = data.get(i);
            data.addAll(el.leaves());
        }
        //копируем все элементы в очередь
        Queue<Node<E>> queue = new LinkedList<>();
        queue.addAll(data);

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
