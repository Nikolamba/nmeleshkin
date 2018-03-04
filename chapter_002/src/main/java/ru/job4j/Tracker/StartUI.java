package ru.job4j.tracker;

/**
 * класс для работы со списком заявок
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StartUI {
    //Получение данных от пользователя
    private final Input input;
    //Хранилище заявок
    private final Tracker tracker;

    /**
     * Конструктор, инициализирующий поля
     * @param input пользовательский ввод
     * @param tracker хранилище заявок
     */
    StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * функция для запуска основоного цикла
     */
    public void init() {
        MenuTracker menu = new MenuTracker(input, tracker);
        int rangeValue = menu.fillAction();
        int[] range = new int[rangeValue];
        fillRange(range);
        int key;
        do {
            menu.showMenu();
            key = input.ask("Выбор: ", range);
            menu.startAction(key);
        } while (key != 6);
    }

    /**
     * функция, заполняющая массив допустимых значения для ввода
     * @param range массив для заполнения
     */
    private void fillRange(int[] range) {
        for (int value = 0; value < range.length; value++) {
            range[value] = value;
        }
    }

    /**
     * Запуск программы
     * @param args аргументы командной строки6
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
