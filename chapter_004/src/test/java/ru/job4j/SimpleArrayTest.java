package ru.job4j;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class SimpleArrayTest {

    private SimpleArray<Integer> simpleArray = new SimpleArray<>();

    @Test
    public void whenUseAddElement() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
        assertThat(simpleArray.get(3), is(4));
    }

    @Test
    public void whenUseSetElement() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.set(1, 5);
        assertThat(simpleArray.get(1), is(5));
        assertThat(simpleArray.get(2), is(2));
        assertThat(simpleArray.get(3), is(3));
    }

    @Test
    public void whenUseDeleteElement() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.delete(1);
        assertThat(simpleArray.get(1), is(3));
        assertThat(simpleArray.get(2), is(4));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUseIterator() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        Iterator<Integer> iterator = simpleArray.iterator();
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