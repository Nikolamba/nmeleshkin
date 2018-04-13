package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */

public class SimpleSetTest {

    private SimpleSet<Integer> set = new SimpleSet<>();

    @Before
    public void setUp() {
        set.add(1);
        set.add(2);
        set.add(3);
    }

    @Test
    public void whenAddDifferentElementsThenShouldAllAdd() {
        assertThat(set.get(0), is(1));
        assertThat(set.get(1), is(2));
        assertThat(set.get(2), is(3));
    }

    @Test
    public void whenAddSameElementsThenAddOneElement() {
        set.add(2);
        set.add(3);
        assertThat(set.get(0), is(1));
        assertThat(set.get(1), is(2));
        assertThat(set.get(2), is(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

}