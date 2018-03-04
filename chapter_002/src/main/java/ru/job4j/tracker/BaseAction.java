package ru.job4j.tracker;

/**
 * Абстрактный класс, реализующий часть интерфейса UserAction
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public abstract class BaseAction implements UserAction {
    private final int key;
    private final String note;

    protected BaseAction(final int key, final String note) {
        this.key = key;
        this.note = note;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.note);
    }
}
