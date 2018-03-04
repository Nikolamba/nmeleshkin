package ru.job4j.tracker;

/**
 * Класс, реализующий удаление выбранной заявки из трекера
 */
class DeleteItem extends BaseAction {

    DeleteItem(int key, String note) {
        super(key, note);
    }

    public void execute(Input input, Tracker tracker) {
        if (tracker.findAll().length == 0) {
            System.out.println("Список заявок пуст");
        } else {
            System.out.println("--------------------Удаление заявки-----------------------");
            String id = input.ask("Введите id заявки, которую нужно удалить: ");
            tracker.delete(id);
            System.out.println("--------------------Удалена заявка id " + id + "-----------------------");
        }
    }
}

/**
 * Класс, реализующий все функции меню
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    //хранилище пользовательских действий
    private UserAction[] actions = new UserAction[10];

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Функция, заполняющая хранилище возможных действий пользователя
     * @return общее количество пунктов меню
     */
    public int fillAction() {
        int index = 0;
        this.actions[index++] = this.new CreateItem(0, "Добавить заявку");
        this.actions[index++] = this.new ShowAllItems(1, "Просмотреть все заявки");
        this.actions[index++] = new MenuTracker.ReplaceItem(2, "Изменить заявку");
        this.actions[index++] = new DeleteItem(3, "Удалить заявку");
        this.actions[index++] = this.new FindById(4, "Найти заявку по ID");
        this.actions[index++] = this.new FindByName(5, "Найти заявки по имени");
        this.actions[index++] = this.new Exit(6, "Выход");

        return index;
    }

    /**
     * Функция, реализующее конкретное пользовательское действие
     * @param key ключ действия
     */
    public void startAction(int key) {
        this.actions[key].execute(input, tracker);
    }

    /**
     * Функция вывода меню
     */
    public void showMenu() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Класс, реализующий добавление заявки в трекер
     */
    private class CreateItem extends BaseAction {

        private CreateItem(int key, String note) {
            super(key, note);
        }
        public void execute(Input input, Tracker tracker) {
            System.out.println("--------------------Добавление новой заявки----------------------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("--------------------Добавлена заявка ID " + item.getId() + "----------------------");
        }
    }

    /**
     * Класс, реализующий изменение заявки в трекере
     */
    private static class ReplaceItem extends BaseAction {

        private ReplaceItem(int key, String note) {
            super(key, note);
        }

        public void execute(Input input, Tracker tracker) {
            if (tracker.findAll().length == 0) {
                System.out.println("Список заявок пуст");
            } else {
                System.out.println("-----------------Изменение заявки--------------------");
                String id = input.ask("Введите id заявки, которую нужно изменить: ");
                String name = input.ask("Введите имя новой заявки: ");
                String desc = input.ask("Введите описание новой заявки: ");
                Item item = new Item(name, desc);
                tracker.replace(id, item);
                System.out.println("------------------Изменена заявка ID " + id + "-------------------");
            }
        }
    }

    /**
     * Класс, реализующий просмотр всех заявок в трекере
     */
    private class ShowAllItems extends BaseAction {

        private ShowAllItems(int key, String note) {
            super(key, note);
        }

        public void execute(Input input, Tracker tracker) {
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
    }

    /**
     * Класс, реализующий поиск заявки в трекере по id
     */
    private class FindById extends BaseAction {

        private FindById(int key, String note) {
            super(key, note);
        }

        public void execute(Input input, Tracker tracker) {
            if (tracker.findAll().length == 0) {
                System.out.println("Список заявок пуст");
            } else {
                System.out.println("-------------------Поиск заявки--------------------");
                String id = input.ask("Введите id заявки, которую нужно найти: ");
                Item item = tracker.findById(id);
                if (item != null) {
                    System.out.println("Заявка id: " + item.getId() + ", name: " + item.getName()
                            + ", description: " + item.getDescription() + ", created: " + item.getCreated());
                } else {
                    System.out.println("Заявка не найдена");
                }
                System.out.println("-------------------Поиск заявки завершен----------------------");
            }
        }
    }

    /**
     * Класс, реализующий поиск заявок в трекере по имени
     */
    private class FindByName extends BaseAction {

        private FindByName(int key, String note) {
            super(key, note);
        }

        public void execute(Input input, Tracker tracker) {
            if (tracker.findAll().length == 0) {
                System.out.println("Список заявок пуст");
            } else {
                System.out.println("---------------------Поиск заявок по имени---------------------");
                String name = input.ask("Введите name заявки, которую нужно найти: ");
                Item[] items = tracker.findByName(name);
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
        }
    }

    /**
     * Класс реализует выход из программы
     */
    private class Exit extends BaseAction {

        private Exit(int key, String note) {
            super(key, note);
        }

        public void execute(Input input, Tracker tracker) { }
    }
}
