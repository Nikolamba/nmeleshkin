package ru.job4j.tracker;

import java.util.Scanner;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    /**
     * функция, запрашивающая ввод от пользователя
     * @param question отображаемый вопрос
     * @return возвращает введенный ответ пользователя
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
