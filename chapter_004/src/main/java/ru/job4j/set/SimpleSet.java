package ru.job4j.set;

import ru.job4j.list.DynamicList;

import java.util.Iterator;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> тип данных данного множества
 */

public class SimpleSet<T> implements Iterable<T> {

    private DynamicList<T> list = new DynamicList<>();

    public void add(T value) {
        if (!hasValue(value)) {
            list.add(value);
        }
    }

    public T get(int index) {
        return list.get(index);
    }

    private boolean hasValue(T value) {
        boolean result = false;
        for (T val : list) {
            if (val.equals(value)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
