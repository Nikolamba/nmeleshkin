package ru.job4j.chess;

/**
 * Класс описывает поведение фигуры "Слон"
 */
public class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position);
    }

    /**
     * функция возвращает массив ячеек поля, который нужно пройти фигуре
     * @param dest ячейка найначения
     * @return массив ячеек поля, который нужно пройти фигуре
     */
    public Cell[] way(Cell dest) {
        int deltaX = dest.getPosX() - this.position.getPosX();
        int deltaY = dest.getPosY() - this.position.getPosY();
        int wayLength = (deltaX != 0) ? Math.abs(deltaX) : Math.abs(deltaY);
        Cell[] wayCell = new Cell[wayLength];
        deltaX = (deltaX != 0) ? deltaX / Math.abs(deltaX) : deltaX;
        deltaY = (deltaY != 0) ? deltaY / Math.abs(deltaY) : deltaY;

        for (int i = 0; i < wayLength; i++) {
            wayCell[i] = new Cell(deltaX, deltaY);
        }
        return wayCell;
    }

    /**
     * Функция проверяет, может ли фигура двигаться
     * @param dest ячейка, в которую нужно пройти фигуре
     * @return true, если фигура может так ходить, false в противном случае
     */
    public boolean canMove(Cell dest) {
        return (this.position.getPosX() == dest.getPosX() || this.position.getPosY() == dest.getPosY());
    }

    /**
     * создает фигуру на указанной ячейке поля
     * @param dest ячейка поля, в которую нужно установить фигуру
     * @return возвращает созданную фигуру
     */
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }
}
