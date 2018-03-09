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
        Cell[] wayCell;
        int wayLength;
        int posX = this.position.getPosX();
        int posY = this.position.getPosY();

        //если движение по вертикали
        if (posX != dest.getPosX()) {
            wayLength = Math.abs(posX - dest.getPosX());
            wayCell = new Cell[wayLength];
            //переменная, определяющая направление движения
            int direction = (posX > dest.getPosX()) ? -1 : 1;
            int counter = 0;
            for (int i = posX + direction; i - dest.getPosX() - direction != 0; i = i + direction) {
                wayCell[counter++] = new Cell(i, posY);
            }
            //если движение по горизонтали
        } else {

            wayLength = Math.abs(posY - dest.getPosY());
            wayCell = new Cell[wayLength];
            //переменная, определяющая направление движения
            int direction = (posY > dest.getPosY()) ? -1 : 1;
            int counter = 0;
            for (int i = posY + direction; i - dest.getPosY() - direction != 0; i = i + direction) {
                wayCell[counter++] = new Cell(posX, i);
            }
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
