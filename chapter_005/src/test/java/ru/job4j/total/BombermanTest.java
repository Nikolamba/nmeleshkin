package ru.job4j.total;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class BombermanTest {

    private Bomberman bomberman = new Bomberman();

    @Test
    public void testBomberman() throws InterruptedException {
        this.bomberman.getCell(1,2).lock();
        this.bomberman.getDirectionsHero().addAll(Arrays.asList(Direction.DOWN, Direction.DOWN,
                Direction.LEFT, Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.RIGHT,
                Direction.DOWN, Direction.DOWN, Direction.UP, Direction.RIGHT));
        bomberman.init();

        bomberman.getHero().join();

    }

}