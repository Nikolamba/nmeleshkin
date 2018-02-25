package ru.job4j.tracker;

/**
 * класс для работы со списком заявок
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StartUI {
    //Константы меню
    private static final String ADD = "0";
    private static final String SHOW_ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_ID = "4";
    private static final String FIND_NAME = "5";
    private static final String EXIT = "6";
    //Получение данных от пользователя
    private final Input input;
    //Хранилище заявок
    private final Tracker tracker;

    /**
     * Конструктор, инициализирующий поля
     * @param input пользовательский ввод
     * @param tracker хранилище заявок
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * функция для запуска основоного цикла
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.replaceItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_ID.equals(answer)) {
                this.findById();
            } else if (FIND_NAME.equals(answer)) {
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * функция реализует добавление новой заявки в хранилище
     */
    private void createItem() {
        System.out.println("--------------------Добавление новой заявки----------------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        tracker.add(item);
        System.out.println("--------------------Добавлена заявка ID " + item.getId() + "----------------------");
    }

    /**
     * Функция для просмотра всех заявок в хранилище
     */
    private void showAllItems() {
        System.out.println("---------------------Список всех заявок---------------------");
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println("Заявка id: " + item.getId() + ", name: " + item.getName()
                        + ", description: " + item.getDescription() + ", created: " + item.getCreated());
            }
        } else {
            System.out.println("Список заявок пуст");
        }

        System.out.println("---------------------Конец списка-------------------------");
    }

    /**
     * Функция для внесение изменений в выбранную заявку
     */
    private void replaceItem() {
        if (tracker.findAll().length == 0) {
            System.out.println("Список заявок пуст");
        } else {
            System.out.println("-----------------Изменение заявки--------------------");
            String id = this.input.ask("Введите id заявки, которую нужно изменить: ");
            String name = this.input.ask("Введите имя новой заявки: ");
            String desc = this.input.ask("Введите описание новой заявки: ");
            Item item = new Item(name, desc);
            tracker.replace(id, item);
            System.out.println("------------------Изменена заявка ID " + id + "-------------------");
        }
    }

    /**
     * Функция для удаления выбранной заявки
     */
    private void deleteItem() {
        if (tracker.findAll().length == 0) {
            System.out.println("Список заявок пуст");
        } else {
            System.out.println("--------------------Удаление заявки-----------------------");
            String id = this.input.ask("Введите id заявки, которую нужно удалить: ");
            tracker.delete(id);
            System.out.println("--------------------Удалена заявка id " + id + "-----------------------");
        }
    }

    /**
     * Функция для поиска заявки по id
     */
    private Item findById() {
        Item item = null;
        if (tracker.findAll().length == 0) {
            System.out.println("Список заявок пуст");
        } else {
            System.out.println("-------------------Поиск заявки--------------------");
            String id = this.input.ask("Введите id заявки, которую нужно найти: ");
            item = tracker.findById(id);
            if (item != null) {
                System.out.println("Заявка id: " + item.getId() + ", name: " + item.getName()
                        + ", description: " + item.getDescription() + ", created: " + item.getCreated());
            } else {
                System.out.println("Заявка не найдена");
            }
            System.out.println("-------------------Поиск заявки завершен----------------------");
        }
        return item;
    }

    /**
     * Функция для поиска заявки по name
     */
    private Item[] findByName() {
        Item[] items = null;
        if (tracker.findAll().length == 0) {
            System.out.println("Список заявок пуст");
        } else {
            System.out.println("---------------------Поиск заявок по имени---------------------");
            String name = this.input.ask("Введите name заявки, которую нужно найти: ");
            items = tracker.findByName(name);
            if (items.length > 0) {
                for (Item item : items) {
                    System.out.println("Заявка id: " + item.getId() + ", name: " + item.getName()
                            + ", description: " + item.getDescription() + ", created: " + item.getCreated());
                }
            } else {
                System.out.println("Заявки с именем " + name + " не найдены");
            }
            System.out.println("---------------------Конец списка-------------------------");
        }
        return items;
    }

    /**
     * Функция для вывода на консоль основного меню
     */
    private void showMenu() {
        System.out.println("Меню:");
        System.out.println("0. Добавить новую заявку");
        System.out.println("1. Просмотреть все заявки");
        System.out.println("2. Изменить заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по ID");
        System.out.println("5. Найти заявки по имени");
        System.out.println("6. Выход");
    }

    /**
     * Запуск программы
     * @param args аргументы командной строки6
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
