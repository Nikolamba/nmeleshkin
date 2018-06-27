package ru.job4j.total;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class BoardTest {

    @Test
    public void testBomberman() throws InterruptedException {
        Menu bombermanGame = new Menu(new Board(8));
        bombermanGame.getDirectionsHero().addAll(Arrays.asList(Direction.DOWN, Direction.DOWN,
                Direction.LEFT, Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.RIGHT,
                Direction.DOWN, Direction.DOWN, Direction.UP, Direction.RIGHT));
        bombermanGame.init();
    }

}