package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {

    @Test
    //когда start и finish четные числа
    public void whenStartAndFinishEven() {
        Counter counter = new Counter();
        int result = counter.add(0, 10);
        assertThat(result, is(30));
    }

    @Test
    //когда start и finish нечетные числа
    public void whenStartAndFinishOdd() {
        Counter counter = new Counter();
        int result = counter.add(1, 11);
        assertThat(result, is(30));
    }
}