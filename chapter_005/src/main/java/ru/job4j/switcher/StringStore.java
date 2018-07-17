package ru.job4j.switcher;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StringStore {
    private StringBuilder string;

    StringStore() {
        string = new StringBuilder();
    }

    public void addValue(int value) {
        string.append(value);
    }

    @Override
    public String toString() {
        return  string.toString();
    }
}
