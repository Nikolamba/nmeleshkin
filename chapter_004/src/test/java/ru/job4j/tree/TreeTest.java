package ru.job4j.tree;

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
public class TreeTest {

    private Tree<Integer> tree = new Tree<>(1);

    @Test
    public void when6ElFindLastThen6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent(), is(false)
        );
    }

    @Test
    public void whenTreeIsBynariShouldReturnTrue() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        assertThat(tree.isBynary(), is(true));
    }

    @Test
    public void whenTreeIsNotBynaryShouldReturnFalse() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBynary(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void shoulThrowNoSuchElementException() {
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        System.out.println(iterator.next());
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);

        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        System.out.println(iterator.next());
        assertThat(iterator.hasNext(), is(true));
        System.out.println(iterator.next());
        assertThat(iterator.hasNext(), is(true));
        System.out.println(iterator.next());
        assertThat(iterator.hasNext(), is(true));
        System.out.println(iterator.next());
        assertThat(iterator.hasNext(), is(true));
        System.out.println(iterator.next());
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        tree.add(1, 2);
        Iterator<Integer> iterator = tree.iterator();
        tree.add(1, 3);
        iterator.next();
    }
}