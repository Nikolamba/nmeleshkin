package ru.job4j.generic;

public class UserStore extends AbstractStore<User> implements Store<User> {

    @Override
    public User findById(String id) {
        Base result = null;
        for (Base element : elementArray) {
            if (element.getId().equals(id)) {
                result = element;
                break;
            }
        }
        return (User) result;
    }
}
