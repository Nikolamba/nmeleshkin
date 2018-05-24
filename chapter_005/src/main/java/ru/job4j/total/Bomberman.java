package ru.job4j.total;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
enum Direction {UP, DOWN, LEFT, RIGHT}

public class Bomberman {

    private final static int SIZE_BOARD = 8;
    private final static Point HERO_START_POINT = new Point(0, 0);

    private final ReentrantLock[][] board;
    private final BombermanHeroes hero;
    //очередь направлеий для героя
    private final Queue<Direction> directionsHero = new LinkedList<>();

    Bomberman() {
        this.board = new ReentrantLock[SIZE_BOARD][SIZE_BOARD];
        for (ReentrantLock[] locks : this.board) {
            for (int i = 0; i < this.board.length; i++) {
                locks[i] = new ReentrantLock();
            }
        }
        this.hero = new Hero(this.board, HERO_START_POINT, directionsHero);
    }

    public void init() {
        this.hero.start();
    }

    public BombermanHeroes getHero() {
        return this.hero;
    }

    public Queue<Direction> getDirectionsHero() {
        return this.directionsHero;
    }

    public ReentrantLock getCell(int x, int y) {
        return this.board[x][y];
    }
}
