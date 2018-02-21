package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Класс учета заявок
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Tracker {
    private final static Random RANDOM = new Random();
    private Item[] items = new Item[100];
    private int position = 0;

    /**
     * функция добавляет заявку в Tracker
     * @param item добавляемая заявка
     * @return возвращает ссылку на добавленную заяку
     */
    public Item add(Item item) {
        item.setId(this.generateID());
        items[position++] = item;
        return item;
    }

    /**
     * функция заменяет заявку
     * @param id идентификатор заменяемой заявки
     * @param item новая заявка
     */
    public void replace(String id, Item item) {
        for (int pos = 0; pos < position; pos++) {
            if (items[pos].getId().equals(id)) {
                items[pos] = item;
            }
        }
    }

    /**
     * функция удаляет выбранную заявку
     * @param id - идентификатор удаляемой заявки
     */
    public void delete(String id) {
        for (int pos = 0; pos < position; pos++) {
            if ((items[pos].getId()).equals(id)) {
                System.arraycopy(items, pos + 1, items, pos, position - pos + 1);
                position--;
            }
        }
    }

    /**
     * получение списка всех заявок
     * @return возвращает ссылку на массив заявок
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * функция поиска заявки
     * @param id идентификатор искомой заявки
     * @return возвращает ссылку на найденную заявку
     */
    public Item findById(String id) {
        Item findItem = null;
        for (int pos = 0; pos < position; pos++) {
            if ((items[pos].getId()).equals(id))  {
                findItem = items[pos];
                break;
            }
        }
        return findItem;
    }

    /**
     * функция поиска заявок
     * @param key имя искомой заявки
     * @return возвращет ссылку на массив найденных заявок
     */
    public Item[] findByName(String key) {
        Item[] findName = new Item[100];
        int counter = 0;
        for (int current = 0; current < position; current++) {
            if ((items[current].getName()).equals(key)) {
                findName[counter] = items[current];
                counter++;
            }
        }
        return Arrays.copyOf(findName, counter);
    }

    /**
     * генерирует уникальный идентификатор для заявки
     * @return уникальный идентификатор
     */
    private String generateID() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }
}
