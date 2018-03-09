package ru.job4j.chess;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Board {
    //размер поля
    private static final int SIZE_BOARD = 8;
    //массив фигур, добавленных на поле
    private Figure[] figures = new Figure[32];
    //количество фигур на поле
    private int countFigures;
    //ячейки поля
    private Cell[] cells = new Cell[64];

    public Board() {
        fillCells();
    }
    /**
     * функция возвращает ячейку поля по указанным координатам
     * @param x координата x поля
     * @param y координата y поля
     * @return возвращает найденную ячейку поля
     */
    public Cell getCell(int x, int y) {
        return this.cells[x * SIZE_BOARD + y];
    }

    /**
     * возвращает количество фигур на поле
     * @return количество фигур на поле
     */
    public int getCountFigures() {
        return this.countFigures;
    }

    /**
     * Добавляет фигуру на поле
     * @param figure фигура для добавления
     * @throws OccupiedWayException путь занят
     */
    public void add(Figure figure) throws OccupiedWayException {
        //если позиция занята, выкидываем исключение
        if (figure.position.isBusy()) {
            throw new OccupiedWayException("Клетка занята. Повторите попытку");
        }

        figure.position.setBusy(true);
        figures[countFigures++] = figure;
    }

    /**
     * Функция осуществляет перемещение фигуры
     * @param source Координата, фигуру в которой нужно переместить
     * @param dest Координата назначения, в которую нужно переместить фигуру
     * @return возвращает true, если перемещение выполнено успешно, false в противном случае
     * @throws ImpossibleMoveException невозможно двигаться
     * @throws OccupiedWayException путь занят
     * @throws FigureNotFoundException фигура не найдена
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        boolean result;

        //если в ячейке нет фигуры выкидываем исключение
        if (!source.isBusy()) {
            throw new FigureNotFoundException("Нет фигуры для перемещения!");
        }
        //если фигура неможет ходить по указанному пути выкидываем исключение
        if (!findFigure(source).canMove(dest)) {
            throw new ImpossibleMoveException("Фигура не может так ходить!");
        }
        Cell[] wayCells = findFigure(source).way(dest);
        //присваиваем виртуальным ячейкам пути реальные значения ячеек дсски
        this.combine(wayCells);
        //если на пути фигуры появляются помехи выкидываем исключение
        for (Cell cell : wayCells) {
            if (cell.isBusy()) {
                throw new OccupiedWayException("Путь для фигуры занят!");
            }
        }

        source.setBusy(false);
        //перемещаем фигуру
        findFigure(source).copy(dest);
        dest.setBusy(true);
        result = true;

        return result;
    }

    /**
     * проводит инициализацию массива ячеек поля
     */
    private void fillCells() {
        int cellsPosition = 0;
        for (int x = 0; x < SIZE_BOARD; x++) {
            for (int y = 0; y < SIZE_BOARD; y++) {
                this.cells[cellsPosition++] = new Cell(x, y);
            }
        }
    }

    /**
     * находит фигуру в указанной позиции поля
     * @param cell ячейка поля
     * @return возвращает найденную фигуру, null, если фигура не найдена
     */
    private Figure findFigure(Cell cell) {
        Figure result = null;
        for (Figure figure : figures) {
            if (figure.position == cell) {
                result = figure;
                break;
            }
        }
        return result;
    }

    /**
     * присваивает виртуальным ячейкам реальные значения ячеек дсски
     * @param wayCells целевой массив для присвоения
     */
    private void combine(Cell[] wayCells) {
        for (int i = 0; i < wayCells.length; i++) {
            wayCells[i] = this.cells[(wayCells[i].getPosX() * SIZE_BOARD) + wayCells[i].getPosY()];
        }
    }
}
