package ru.job4j.music.dao;

import java.util.List;
import java.util.Properties;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public interface Dao<T> {
    boolean create(T obj);
    boolean edit(int id, Properties data);
    boolean delete(int id);
    T findById(int id);
    List<T> findAll();
}
