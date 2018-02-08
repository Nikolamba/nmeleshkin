package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class FactorialTest {
    private Factorial factorial = new Factorial();
    private int result;

    @Test
    //проверяет, что факториал 5 = 120
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        result = factorial.calc(5);
        assertThat(result, is(120));
    }

    @Test
    //проверяет, что факториал 0 = 1
    public void whenCalculateFactorialForZeroThenOne() {
        result = factorial.calc(0);
        assertThat(result, is(1));
    }

}