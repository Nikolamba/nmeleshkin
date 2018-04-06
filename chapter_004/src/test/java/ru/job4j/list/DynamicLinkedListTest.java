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
public class DynamicLinkedListTest {

    private DynamicLinkedList<Integer> list = new DynamicLinkedList<>();

    @Test
    public void whenAddElements() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.size(), is(3));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(4));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUseIterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
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
    public void whenAddElementShouldConcurentException() {
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        list.add(2);
        iterator.next();
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUseIteratorForEmptyListShouldNoSuchElementException() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }
}