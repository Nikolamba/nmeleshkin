package ru.job4j.list;

public class SimpleStack<T> {

    private DynamicLinkedList<T> list = new DynamicLinkedList<>();

    public void push(T value) {
        list.add(value);
    }

    public T pool() {
        if (list.size() == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return result;
    }
}
