package ru.job4j.list;

public class SimpleQueue<T> {

    private DynamicLinkedList<T> list = new DynamicLinkedList<>();

    public void push(T value) {
        list.add(value);
    }

    public T pool() {
        T result = list.get(0);
        list.removeFirst();
        return result;
    }
}
