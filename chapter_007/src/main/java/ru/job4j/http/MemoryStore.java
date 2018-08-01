package ru.job4j.http;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store {

    private CopyOnWriteArrayList<User> userStore;
    private static MemoryStore instance = new MemoryStore();

    private MemoryStore() {
        userStore = new CopyOnWriteArrayList<>();
    }

    static MemoryStore getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        userStore.add(user);
    }

    @Override
    public void update(int id, String newName) {
        this.findById(id).setName(newName);
    }

    @Override
    public void delete(User user) {
        userStore.remove(user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore);
    }

    @Override
    public User findById(int id) {
        User userResult = null;
        for (User user : userStore) {
            if (user.getId() == id) {
                userResult = user;
                break;
            }
        }
        return userResult;
    }
}
