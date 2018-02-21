package ru.job4j.tracker;

/**
 * интерфейс для реализации пользовательского ввода
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public interface Input {
    /**
     * @param question вопрос
     * @return ответ пользователя
     */
    String ask(String question);
}
