package ru.job4j.generic;

public abstract class AbstractStore<T extends Base> {

    private SimpleArray<T> elementArray = new SimpleArray<>();

    public void add(T model) {
        elementArray.add(model);
    }

    public boolean replace(String id, T model) {
        boolean result = false;
        if (findById(id) != null) {
            elementArray.set(elementArray.getIndex(findById(id)), model);
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            elementArray.delete(elementArray.getIndex(findById(id)));
            result = true;
        }
        return result;
    }

    public T findById(String id) {
        T result = null;
        for (T element : elementArray) {
            if (element.getId().equals(id)) {
                result = element;
                break;
            }
        }
        return result;
    }
}
