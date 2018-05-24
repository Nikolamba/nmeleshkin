package ru.job4j.total;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Hero extends BombermanHeroes {

    Hero(ReentrantLock[][] board, Point startPoint, Queue<Direction> directions){
        super(board, startPoint, directions);
    }

}
