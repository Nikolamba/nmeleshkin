package ru.job4j.tracker;

import java.util.List;

/**
 * Класс учета заявок
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Tracker implements AutoCloseable {
    private InitSql sqlStorage;

    Tracker(String databaseURL, String userName, String userPass) {
        this.sqlStorage = new InitSql(databaseURL, userName, userPass);
    }

    public Item add(Item item) {
        sqlStorage.add(item);
        return item;
    }

    public void replace(String id, Item item) {
        sqlStorage.replace(id, item);
    }

    public void delete(String id) {
        sqlStorage.delete(id);
    }

    public List<Item> findAll() {
        return sqlStorage.findAll();
    }

    public Item findById(String id) {
        return sqlStorage.findById(id);
    }

    public List<Item> findByName(String key) {
        return sqlStorage.findByName(key);
    }



    @Override
    public void close() throws Exception {
        sqlStorage.close();
    }
}
