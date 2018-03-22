package ru.job4j.chess;

/**
 * Класс, описывающий ячейку шахматной доски
 * @author Nikolay Melehskin (sol.of.f@mail.ru)
 * @version 0.1
 */

public class Cell {
    private int posX;
    private int posY;

    public Cell(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
