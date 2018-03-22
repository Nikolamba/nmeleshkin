package ru.job4j.chess;

/**
 * класс реализует поведение меню программы
 */
public class MenuChess {
    private Input input;
    private Board board;
    //массив, содержащий доступные действия пользователя
    private UserActions[] userActions = new UserActions[10];

    public MenuChess(Input input, Board board) {
        this.input = input;
        this.board = board;
    }

    /**
     * заполняет массив меню
     * @return возвращает количество доступных пунктов меню
     */
    public int fillAction() {
        int index = 0;
        userActions[index++] = new AddFigure(0, "Добавить фигуру");
        userActions[index++] = new MoveFigure(1, "Переместить фигуру");
        userActions[index++] = new GetAllFigures(2, "Показать все фигуры");
        userActions[index++] = new Exit(3, "Выход");
        return index;
    }

    /**
     * Отображает меню
     */
    public void showMenu() {
        for (UserActions ua : userActions) {
            if (ua != null) {
                System.out.println(ua.info());
            }
        }
    }

    /**
     * запускает действие меню, указанное в поле key
     * @param key ключ меню, которое нужно запустить
     */
    public void execute(int key) {
        userActions[key].execute(this.input);
    }

    /**
     * релизует добавление фигуры на поле
     */
    private class AddFigure implements UserActions {
        private int key;
        private String note;

        private AddFigure(int key, String note) {
            this.key = key;
            this.note = note;
        }

        public int key() {
            return this.key;
        }

        public String info() {
            return String.format("%s. %s", key, note);
        }

        public void execute(Input input) {
            System.out.println("------------------Добавление фигуры-------------------");

            boolean result;
            int coorX, coorY;
            do {
                try {
                    coorX = Integer.valueOf(input.ask("Введите первую кооридинату поля: "));
                    coorY = Integer.valueOf(input.ask("Введите вторую координату поля: "));
                    Cell cell = board.getCell(coorX, coorY);
                    if (cell != null) {
                        throw new OccupiedWayException("Клетка занята. Повторите попытку");
                    }
                    cell = board.createCell(coorX, coorY);
                    board.add(new Bishop(cell));
                    result = true;
                } catch (OccupiedWayException owe) {
                    System.out.println(owe.getMessage());
                    break;
                }
            } while (!result);

            System.out.println("------------- Окончание добавления фигуры ----------");
        }
    }

    /**
     * реализует движение фигуры по полю
     */
    private class MoveFigure implements UserActions {
        private int key;
        private String note;

        private MoveFigure(int key, String note) {
            this.key = key;
            this.note = note;
        }

        public int key() {
            return this.key;
        }

        public String info() {
            return String.format("%s. %s", key, this.note);
        }

        public void execute(Input input) {
            if (board.getCountFigures() == 0) {
                System.out.println("Нет фигур для перемещения");
            } else {
                System.out.println("------------------ Перемещаем фигуру ----------------------");
                boolean result;
                int coorX, coorY;
                do {
                    try {
                        coorX = Integer.valueOf(input.ask("Откуда перемещаем? Введите первую координату поля: "));
                        coorY = Integer.valueOf(input.ask("Откуда перемещаем? Введите вторую координату поля: "));
                        Cell source = board.getCell(coorX, coorY);
                        coorX = Integer.valueOf(input.ask("Куда перемещаем? Введите первую координату поля: "));
                        coorY = Integer.valueOf(input.ask("Куда перемещаем? Введите вторую координату поля: "));
                        Cell dest = new Cell(coorX, coorY);
                        board.move(source, dest);
                        result = true;
                    } catch (FigureNotFoundException ffe) {
                        System.out.println(ffe.getMessage() + " Попробуйте еще раз");
                        break;
                    } catch (ImpossibleMoveException ime) {
                        System.out.println(ime.getMessage() + " Попробуйте еще раз");
                        break;
                    } catch (OccupiedWayException owe) {
                        System.out.println(owe.getMessage() + " Попробуйте еще раз");
                        break;
                    }

                } while (!result);

                System.out.println("------------------ Перемещение закончено ----------------------");
            }
        }
    }

    /**
     * реализует просмотр всех фигур на поле
     */
    private class GetAllFigures implements UserActions {
        private int key;
        private String note;

        private GetAllFigures(int key, String note) {
            this.key = key;
            this.note = note;
        }

        public int key() {
            return this.key;
        }

        public String info() {
            return String.format("%s. %s", key, this.note);
        }

        public void execute(Input input) {
            if (board.getCountFigures() == 0) {
                System.out.println("На доске нет фигур");
            } else {
                System.out.println("----------------- Показаны все фигуры -------------------");
                Figure[] allFigures = board.getAllFigures();
                for (Figure figure : allFigures) {
                    System.out.println(figure.getClass().getSimpleName() + " (" + figure.position.getPosX()
                    + " : " + figure.position.getPosY() + ")");
                }
            }
            System.out.println("------------------------------------------------------------");
        }
    }

    /**
     * реализует выход из программы
     */
    private class Exit implements UserActions {
        private int key;
        private String note;

        private Exit(int key, String note) {
            this.key = key;
            this.note = note;
        }

        public int key() {
            return this.key;
        }

        public String info() {
            return String.format("%s. %s", key, this.note);
        }

        public void execute(Input input) {

        }
    }
}
