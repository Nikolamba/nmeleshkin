package ru.job4j.chess;

/**
 * Интерфейс, описывающий общее поведение пользовательского ввода
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
