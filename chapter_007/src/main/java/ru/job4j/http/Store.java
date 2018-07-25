package ru.job4j.http;

import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public interface Store {
    void add(User user);
    void update(int id, String newName);
    void delete(User user);
    List<User> findAll();
    User findById(int id);
}
