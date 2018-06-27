package ru.job4j.total;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */

public class Board {

    private final ReentrantLock[][] board;

    Board(int size) {
        this.board = new ReentrantLock[size][size];
        for (ReentrantLock[] locks : this.board) {
            for (int i = 0; i < this.board.length; i++) {
                locks[i] = new ReentrantLock();
            }
        }
    }

    public boolean move(Point sourse, Point dest) {
        boolean result = false;

        if (dest.x < 0 || dest.y < 0 || dest.x > this.board.length || dest.y > this.board.length) {
            return false;
        }

        if (!this.board[dest.x][dest.y].isLocked()) {
            try {
                if (this.board[dest.x][dest.y].tryLock(500, TimeUnit.MILLISECONDS)) {
                    result = true;
                    if (sourse != dest) {
                        this.board[sourse.x][sourse.y].unlock();
                    }
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        return result;
    }
}
