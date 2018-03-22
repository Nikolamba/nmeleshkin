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
    public void add(Figure figure) {
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

        //если в ячейке нет фигуры выкидываем исключение
        if (source == null) {
            throw new FigureNotFoundException("Нет фигуры для перемещения!");
        }
        //если фигура неможет ходить по указанному пути выкидываем исключение
        if (!findFigure(source).canMove(dest)) {
            throw new ImpossibleMoveException("Фигура не может так ходить!");
        }
        Cell[] wayCells = findFigure(source).way(dest);
        //присваиваем виртуальным ячейкам пути реальные значения ячеек дсски
        this.combine(wayCells, source);
        //если на пути фигуры появляются помехи выкидываем исключение
        for (Cell cell : wayCells) {
            if (cell != null) {
                throw new OccupiedWayException("Путь для фигуры занят!");
            }
        }

        dest = this.createCell(dest.getPosX(), dest.getPosY());
        int index = this.findFigureIndex(source);
        figures[index] = this.findFigure(source).copy(dest);
        cells[source.getPosX() * SIZE_BOARD + source.getPosY()] = null;

        return true;
    }

    /**
     * инициализирует ячейку поля
     */
    public Cell createCell(int x, int y) {
        this.cells[x * SIZE_BOARD + y] = new Cell(x, y);
        return this.cells[x * SIZE_BOARD + y];
    }

    /**
     * возвращает все фигуры, которые есть на поле
     * @return массив фигур, находящихся на поле
     */
    public Figure[] getAllFigures() {
        Figure[] result = new Figure[this.countFigures];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.figures[i];
        }
        return result;
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

    private int findFigureIndex(Cell cell) {
        int result = -1;
        for (int i = 0; i < figures.length; i++) {
            if (figures[i].position == cell) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * присваивает виртуальным ячейкам реальные значения ячеек дсски
     * @param wayCells целевой массив для присвоения
     */
    private void combine(Cell[] wayCells, final Cell source) {
        Cell start = new Cell(source.getPosX(), source.getPosY());

        for (int i = 0; i < wayCells.length; i++) {
            Cell cell = this.cells[(start.getPosX() * SIZE_BOARD) + (wayCells[i].getPosX() * SIZE_BOARD)
                    + start.getPosY() + wayCells[i].getPosY()];
            start.setPosX(start.getPosX() + wayCells[i].getPosX());
            start.setPosY(start.getPosY() + wayCells[i].getPosY());
            wayCells[i] = cell;
        }
    }


}
