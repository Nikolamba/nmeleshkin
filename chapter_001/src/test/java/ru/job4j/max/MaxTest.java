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
    public void whenFirstLessSecond() {
        result = Max.max(1, 2);
        assertThat(result, is(2));
    }
    @Test
    public void whenFirstMoreSecond() {
        result = Max.max(3, 2);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstEqualSecond() {
        result = Max.max(4, 4);
        assertThat(result, is(4));
    }

}