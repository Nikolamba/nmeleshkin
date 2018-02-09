package ru.job4j.array;

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
    //когда bound = 0
    public void whenBound0() {
        Square square = new Square();
        int[] result = square.calculate(0);
        int[] expected = new int[0];
        assertThat(result, is(expected));
    }

    @Test
    //когда bound = 1
    public void whenBound1() {
        Square square = new Square();
        int[] result = square.calculate(1);
        int[] expected = new int[]{1};
        assertThat(result, is(expected));
    }

    @Test
    //когда bound = 6
    public void whenBound6() {
        Square square = new Square();
        int[] result = square.calculate(6);
        int[] expected = new int[] {1, 4, 9, 16, 25, 36};
        assertThat(result, is(expected));
    }
}