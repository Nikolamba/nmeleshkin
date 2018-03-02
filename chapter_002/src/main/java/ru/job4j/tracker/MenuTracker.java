package ru.job4j.tracker;

/**
 * Класс, реализующий удаление выбранной заявки из трекера
 */
class DeleteItem implements UserAction {
    public int key() {
        return 3;
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

    public String info() {
        return String.format("%s. %s", this.key(), "Удалить заявку");
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
        this.actions[index++] = this.new CreateItem();
        this.actions[index++] = this.new ShowAllItems();
        this.actions[index++] = new MenuTracker.ReplaceItem();
        this.actions[index++] = new DeleteItem();
        this.actions[index++] = this.new FindById();
        this.actions[index++] = this.new FindByName();
        this.actions[index++] = this.new Exit();

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
    private class CreateItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------------------Добавление новой заявки----------------------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("--------------------Добавлена заявка ID " + item.getId() + "----------------------");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новую заявку");
        }
    }

    /**
     * Класс, реализующий изменение заявки в трекере
     */
    private static class ReplaceItem implements UserAction {
        public int key() {
            return 2;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Изменить заявку");
        }
    }

    /**
     * Класс, реализующий просмотр всех заявок в трекере
     */
    private class ShowAllItems implements UserAction {
        public int key() {
            return 1;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Просмотреть все заявки");
        }
    }

    /**
     * Класс, реализующий поиск заявки в трекере по id
     */
    private class FindById implements UserAction {
        public int key() {
            return 4;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по ID");
        }
    }

    /**
     * Класс, реализующий поиск заявок в трекере по имени
     */
    private class FindByName implements UserAction {
        public int key() {
            return 5;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявки по имени");
        }
    }

    /**
     * Класс реализует выход из программы
     */
    private class Exit implements UserAction {
        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {

        }

        public String info() {
            return String.format("%s. %s", this.key(), "Выход");
        }
    }
}
