package ru.job4j.http;

import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public interface Store<T> {
    void add(T user);
    void update(int id, String newName, String login, String email, String autorization, String country, String city);
    void delete(T user);
    List<T> findAll();
    T findById(int id);
    T findByLogin(String login);
}
