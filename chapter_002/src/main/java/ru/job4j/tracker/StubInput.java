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

    public void resetPosition() {
        this.position = 0;
    }

    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (key == value) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Выход за границы допустимых значений");
        }
    }
}
