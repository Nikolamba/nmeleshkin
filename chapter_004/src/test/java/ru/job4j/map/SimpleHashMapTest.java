package ru.job4j.map;

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
public class SimpleHashMapTest {

    private SimpleHashMap<Integer, String> map = new SimpleHashMap<>();

    @Test
    public void whenInsertElementsShouldGetElements() {
        map.insert(1, "111");
        map.insert(2, "222");
        map.insert(3, "333");
        map.insert(4, "444");
        map.insert(5, "555");

        assertThat(map.get(1), is("111"));
        assertThat(map.get(2), is("222"));
        assertThat(map.get(5), is("555"));

        assertThat(map.size(), is(5));
    }

    @Test
    public void whenInsertElementThenDeleteElement() {
        map.insert(1, "111");
        map.insert(2, "222");
        map.insert(3, "333");
        assertThat(map.delete(2), is(true));
        assertThat(map.size(), is(2));
        assertThat(map.get(2), is((String) null));
    }

    @Test (expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocation() {
        map.insert(1, "111");
        map.insert(2, "222");
        map.insert(3, "333");

        Iterator<Integer> iterator = map.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test
    public void shouldReturnFalseIfMapIsEmpty() {
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenChangeMapShouldConcurrentModificationException() {
        map.insert(1, "111");
        map.insert(2, "222");
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(map.insert(3, "333"), is(true));
        iterator.next();
    }
}