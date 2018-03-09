package ru.job4j.chess;

/**
 * Класс, описывающий общее поведение для всех фигур
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public abstract class Figure {
    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    abstract Cell[] way(Cell dest) throws ImpossibleMoveException;

    abstract Figure copy(Cell dest);

    abstract boolean canMove(Cell dest);
}
