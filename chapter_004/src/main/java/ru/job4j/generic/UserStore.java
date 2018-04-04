package ru.job4j.generic;

public class UserStore implements Store {

    private SimpleArray<User> elementArray = new SimpleArray<>();

    @Override
    public void add(Base model) {
        elementArray.add((User)model);
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        if (findById(id) != null) {
            elementArray.set(elementArray.getIndex((User)findById(id)), (User)model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            elementArray.delete(elementArray.getIndex((User)findById(id)));
            result = true;
        }
        return result;
    }

    @Override
    public Base findById(String id) {
        Base result = null;
        for (Base element : elementArray) {
            if (element.getId().equals(id)) {
                result = element;
                break;
            }
        }
        return result;
    }
}
