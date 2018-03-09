package ru.job4j.chess;

/**
 * класс, реализующий запуск программы
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StartChess {
    private Input input;
    private Board board;

    public StartChess(Input input, Board board) {
        this.input = input;
        this.board = board;
    }

    /**
     * функция запускает основной цикл программы
     */
    public void init() {
        MenuChess menu = new MenuChess(this.input, this.board);
        menu.fillAction();
        int key;
        do {
            menu.showMenu();
            key = Integer.valueOf(input.ask("Выберите доступный пункт меню: "));
            menu.execute(key);
        } while (key != 2);
    }

    public static void main(String[] args) {
        new StartChess(new ConsoleInput(), new Board()).init();
    }

}
