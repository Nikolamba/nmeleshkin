package ru.job4j.generic;

public class RoleStore extends AbstractStore<Role> implements Store<Role> {

    @Override
    public Role findById(String id) {
        Base result = null;
        for (Base element : elementArray) {
            if (element.getId().equals(id)) {
                result = element;
                break;
            }
        }
        return (Role) result;
    }
}
