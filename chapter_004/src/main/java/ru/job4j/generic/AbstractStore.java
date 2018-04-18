package ru.job4j.generic;

public abstract class AbstractStore<Base> {

    protected SimpleArray<Base> elementArray = new SimpleArray<>();

    public void add(Base model) {
        elementArray.add(model);
    }

    public boolean replace(String id, Base model) {
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

    abstract Base findById(String id);
}
