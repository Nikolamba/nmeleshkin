package ru.job4j.generic;

public class RoleStore implements Store {

    private SimpleArray<Role> elementArray = new SimpleArray<>();

    @Override
    public void add(Base model) {
        elementArray.add((Role)model);
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        if (findById(id) != null) {
            elementArray.set(elementArray.getIndex((Role)findById(id)), (Role)model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            elementArray.delete(elementArray.getIndex((Role)findById(id)));
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
