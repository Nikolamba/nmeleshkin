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
        menu.fillAction();
        int key;
        do {
            menu.showMenu();
            key = Integer.decode(input.ask("Select: "));
            menu.startAction(key);

        } while (key != 6);
    }

    /**
     * Запуск программы
     * @param args аргументы командной строки6
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
