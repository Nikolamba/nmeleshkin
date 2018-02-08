package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class MaxTest {
    private int result;

    @Test
    //max(int, int), когда первое число меньше второго
    public void whenFirstLessSecond() {
        result = Max.max(1, 2);
        assertThat(result, is(2));
    }
    @Test
    //max(int, int), когда первое число больше второго
    public void whenFirstMoreSecond() {
        result = Max.max(3, 2);
        assertThat(result, is(3));
    }

    @Test
    //max(int, int), когда первое число = второму
    public void whenFirstEqualSecond() {
        result = Max.max(4, 4);
        assertThat(result, is(4));
    }

    @Test
    //max(int, int, int), когда первое число больше 2-х остальных
    public void whenFirstLargest() {
        result = Max.max(3, 2, 1);
        assertThat(result, is(3));
    }

    @Test
    //max(int, int, int), когда второе число больше 2-х остальных
    public void whenSecondLargest() {
        result = Max.max(3, 4, 2);
        assertThat(result, is(4));
    }

    @Test
    //max(int, int, int), когда третье число больше 2-х остальных
    public void whenThirdLargest() {
        result = Max.max(4, 5, 6);
        assertThat(result, is(6));
    }

    @Test
    //max(int, int, int), когда числа равны
    public void whenNumbersEqual() {
        result = Max.max(10, 10, 10);
        assertThat(result, is(10));
    }

}