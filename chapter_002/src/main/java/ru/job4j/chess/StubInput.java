package ru.job4j.chess;

/**
 * Класс пользовательского ввода для поддержки автоматизированных тестов
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StubInput implements Input {
    private final String[] value;
    private int index = 0;

    public StubInput(String[] value) {
        this.value = value;
    }
    public String ask(String question) {
        return this.value[this.index++];
    }
}
