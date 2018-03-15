package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Класс учета заявок
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Tracker {
    private final static Random RANDOM = new Random();
    private List<Item> items = new ArrayList<>();

    /**
     * функция добавляет заявку в Tracker
     * @param item добавляемая заявка
     * @return возвращает ссылку на добавленную заяку
     */
    public Item add(Item item) {
        item.setId(this.generateID());
        items.add(item);
        return item;
    }

    /**
     * функция заменяет заявку
     * @param id идентификатор заменяемой заявки
     * @param item новая заявка
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
                item.setId(items.get(index).getId());
                items.set(index, item);
                break;
            }
        }
    }

    /**
     * функция удаляет выбранную заявку
     * @param id - идентификатор удаляемой заявки
     */
    public void delete(String id) {
        for (Item localItem : items) {
            if (localItem.getId().equals(id)) {
                items.remove(localItem);
            }
        }
    }

    /**
     * получение списка всех заявок
     * @return возвращает ссылку на список заявок
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * функция поиска заявки
     * @param id идентификатор искомой заявки
     * @return возвращает ссылку на найденную заявку
     */
    public Item findById(String id) {
        Item findItem = null;
        for (Item localItem : items) {
            if (localItem.getId().equals(id)) {
                findItem = localItem;
                break;
            }
        }
        return findItem;
    }

    /**
     * функция поиска заявок
     * @param key имя искомой заявки
     * @return возвращет ссылку на список найденных заявок
     */
    public List<Item> findByName(String key) {
        List<Item> findName = new ArrayList<>();
        for (Item localItem : items) {
            if (localItem.getName().equals(key)) {
                findName.add(localItem);
            }
        }
        return findName;
    }

    /**
     * генерирует уникальный идентификатор для заявки
     * @return уникальный идентификатор
     */
    private String generateID() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }
}
