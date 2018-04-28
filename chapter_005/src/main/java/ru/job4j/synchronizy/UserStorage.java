package ru.job4j.synchronizy;

import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@ThreadSafe
public class UserStorage {

    private Set<User> storage = new HashSet<>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!storage.contains(user)) {
            storage.add(user);
            result = true;
        }
        return result;
    }

    public boolean update(User user) {
        boolean result = false;
        if (storage.contains(user)) {
            synchronized (this.findUserByID(user.getId())) {
                this.findUserByID(user.getId()).setMount(user.getMount());
                result = true;
            }
        }
        return result;
    }

    public boolean delete(User user) {
        boolean result = false;
        if (storage.contains(user)) {
            synchronized (this.findUserByID(user.getId())) {
                storage.remove(user);
                result = true;
            }
        }
        return result;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (storage.contains(new User(fromId, 0)) && storage.contains(new User(toId, 0))
                && this.findUserByID(fromId).getMount() >= amount) {
            synchronized (this.findUserByID(fromId)) {
                synchronized (this.findUserByID(toId)) {
                    this.update(new User(fromId, this.findUserByID(fromId).getMount() - amount));
                    this.update(new User(toId, this.findUserByID(toId).getMount() + amount));
                    result = true;
                }
            }
        }
        return result;
    }

    private User findUserByID(int id) {
        User result = null;
        for (User user : this.storage) {
            if (user.equals(new User(id, 0))) {
                result = user;
                break;
            }
        }
        return result;
    }

    public int getMountById(int id) {
        return this.findUserByID(id).getMount();
    }
}
