package ru.job4j.tracker;

/**
 * Интерфейс, описывающий пользовательское действие
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public interface UserAction {
    int key();
    void execute(Input input, Tracker tracker);
    String info();
}
