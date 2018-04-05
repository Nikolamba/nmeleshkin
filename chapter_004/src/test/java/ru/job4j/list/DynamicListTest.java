package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */

public class DynamicListTest {

    private DynamicList<Integer> dynamicList = new DynamicList<>();

    @Test
    public void whenAddFiveElements() {
        dynamicList.add(1);
        dynamicList.add(2);
        dynamicList.add(3);
        dynamicList.add(4);
        dynamicList.add(5);
        assertThat(dynamicList.get(0), is(1));
        assertThat(dynamicList.get(4), is(5));
    }

    @Test
    public void whenUseGet() {
        dynamicList.add(1);
        dynamicList.add(2);
        dynamicList.add(3);
        assertThat(dynamicList.get(0), is(1));
        assertThat(dynamicList.get(2), is(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUseIteratorShouldNoSuchElementException() {
        dynamicList.add(1);
        dynamicList.add(2);
        dynamicList.add(3);
        Iterator<Integer> iterator = dynamicList.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenUserIteratorShouldConcurentException() {
        dynamicList.add(1);
        Iterator<Integer> iterator = dynamicList.iterator();
        dynamicList.add(2);
        iterator.next();
    }
}