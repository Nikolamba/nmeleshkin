package ru.job4j.total;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

enum Direction { UP, DOWN, LEFT, RIGHT }

public class Menu {
    private final static Point HERO_START_POINT = new Point(0, 0);

    private final Board board;
    //список направлений для героя
    private final Queue<Direction> directionsHero = new LinkedList<>();
    private BombermanHeroes hero;

    Menu(Board board) {
        this.board = board;
        this.hero = new Hero(HERO_START_POINT);
    }

   public void init() throws InterruptedException {

       Thread heroThread = new Thread(() -> {
           this.board.move(HERO_START_POINT, HERO_START_POINT);

           while (!directionsHero.isEmpty()) {
               Point destPoint = getDestPoint(directionsHero.poll());

               while (!board.move(hero.getPoint(), destPoint)) {
                   destPoint = getDestPoint(directionsHero.poll());
               }
               hero.setPoint(destPoint);
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException ie) {
                   ie.printStackTrace();
               }
           }
       });
       heroThread.start(); heroThread.join();
    }

    private Point getDestPoint(Direction direction) {
        Point result = new Point(0, 0);
        switch (direction) {
            case LEFT: result.setLocation(-1, 0); break;
            case RIGHT: result.setLocation(1, 0); break;
            case DOWN: result.setLocation(0, 1); break;
            case UP: result.setLocation(0, -1); break;
            default: result.setLocation(0, 0);
        }
        result.setLocation(this.hero.getPoint().x + result.x, this.hero.getPoint().y + result.y);
        return result;
    }

    public Queue<Direction> getDirectionsHero() {
        return directionsHero;
    }

    public Board getBoard() {
        return board;
    }
}
