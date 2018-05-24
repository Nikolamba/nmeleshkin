package ru.job4j.total;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Monster extends BombermanHeroes {

    public Monster(ReentrantLock[][] board, Point startPoint, Queue<Direction> directions) {
        super(board, startPoint, directions);
    }
}
