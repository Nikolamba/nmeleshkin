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
public class SimpleLinkedSetTest {

    private SimpleLinkedSet<Integer> linkedSet = new SimpleLinkedSet<>();

    @Before
    public void setUp() {
        linkedSet.add(1);
        linkedSet.add(2);
        linkedSet.add(3);
    }

    @Test
    public void whenAddDifferentElementsThenShouldAllAdd() {
        assertThat(linkedSet.get(0), is(1));
        assertThat(linkedSet.get(1), is(2));
        assertThat(linkedSet.get(2), is(3));
    }

    @Test
    public void whenAddSameElementsThenAddOneElement() {
        linkedSet.add(2);
        linkedSet.add(3);
        assertThat(linkedSet.get(0), is(1));
        assertThat(linkedSet.get(1), is(2));
        assertThat(linkedSet.get(2), is(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> iterator = linkedSet.iterator();
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