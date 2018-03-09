package ru.job4j.chess;

/**
 * Интерфейс, описывающий общее поведение пользовательских действий
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public interface UserActions {

    int key();

    void execute(Input input);

    String info();
}
