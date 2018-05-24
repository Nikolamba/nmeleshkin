package ru.job4j.total;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public abstract class BombermanHeroes extends Thread {

    private ReentrantLock[][] board;
    private Point point;
    private Queue<Direction> directions;

    BombermanHeroes(ReentrantLock[][] board, Point startPoint, Queue<Direction> directions) {
        this.board = board;
        this.point = startPoint;
        this.directions = directions;
    }

    @Override
    public void run() {
        board[point.x][point.y].lock();

        while (!directions.isEmpty()) {
            //получаем точку, в которую можно пойти
            Point newPoint = makeStep(directions.poll());
            System.out.println(getClass().getSimpleName() + " " + point.x + " " + point.y);
            try {
                //пытаемся захватить новую точку
                if (this.board[newPoint.x][newPoint.y].tryLock()
                        || this.board[newPoint.x][newPoint.y].tryLock(500, TimeUnit.MILLISECONDS)) {
                    this.board[this.point.x][this.point.y].unlock();
                    this.point = newPoint;
                    sleep(1000);
                }
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    private Point makeStep(Direction direction) {
        Point p = new Point(0, 0);
        boolean condition = false;

        //определяем точку по направлению
        while (!condition) {
            switch (direction) {
                case UP: p.setLocation(this.point.x, this.point.y - 1); break;
                case DOWN: p.setLocation(this.point.x, this.point.y + 1); break;
                case LEFT: p.setLocation(this.point.x - 1, this.point.y); break;
                case RIGHT: p.setLocation(this.point.x + 1, this.point.y); break;
            }
            //если вышли за границы доски, извлекаем другое направление
            if (p.x >= this.board.length || p.x < 0 || p.y >= this.board.length || p.y < 0) {
                System.out.println("Невозможно пойти");
                if (!directions.isEmpty()) {
                    direction = directions.poll();
                }
                //если очередь направлений пуста, завершаем поток
                else {
                    Thread.currentThread().interrupt();
                }
            } else {
                condition = true;
            }
        }
        return p;
    }

    public int getPosX() {
        return this.point.x;
    }

    public int getPosY() {
        return this.point.y;
    }
}
