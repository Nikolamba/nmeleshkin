package ru.job4j.tracker;

/**
 * класс для работы со списком заявок
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StartUI {
    //Получение данных от пользователя
    private final StubInput input;
    //Хранилище заявок
    private final Tracker tracker;

    /**
     * Конструктор, инициализирующий поля
     * @param input пользовательский ввод
     * @param tracker хранилище заявок
     */
    StartUI(StubInput input, Tracker tracker) {
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
        int stop = 2000;
        do {
            menu.showMenu();
            key = input.ask("Выбор: ", range);
            if (key == 6) {
                input.resetPosition();
                key = input.ask("Выбор: ", range);
            }
            menu.startAction(key);
            stop--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (stop != 0);
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
        String databaseURL = "jdbc:postgresql://localhost:5432/tracker";
        String userName = "postgres";
        String userPass = "123456";

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] ask = {"0", "task", "task_desc", "6"};
        try (Tracker tracker = new Tracker(databaseURL, userName, userPass)) {
            new StartUI(new StubInput(ask), tracker).init();
            //new StartUI(new ValidateInput(new ConsoleInput()), tracker).init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
