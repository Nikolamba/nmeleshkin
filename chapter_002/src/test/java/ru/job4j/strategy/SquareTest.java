package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {

    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        StringBuilder expectedString = new StringBuilder();
        expectedString.append("+++++");
        expectedString.append(System.lineSeparator());
        expectedString.append("+   +");
        expectedString.append(System.lineSeparator());
        expectedString.append("+   +");
        expectedString.append(System.lineSeparator());
        expectedString.append("+++++");
        assertThat(square.draw(), is(expectedString.toString()));
    }
}