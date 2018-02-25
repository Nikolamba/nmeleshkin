package ru.job4j.tracker;

/**
 * Класс для реализации автоматизированного ввода для тестирования
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StubInput implements Input {
    private final String[] value;
    private int position;
    public StubInput(String[] value) {
        this.value = value;
    }
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }
}
