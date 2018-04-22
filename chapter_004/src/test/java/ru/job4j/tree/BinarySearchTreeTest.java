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
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree = new BinarySearchTree<>(0);

    @Test
    public void whenAddValues() {
        tree.add(10);
        tree.add(2);
        tree.add(15);
        tree.add(-1);
        assertThat(tree.getRoot().leftTree.value, is(-1));
        assertThat(tree.getRoot().rightTree.value, is(10));
        assertThat(tree.getRoot().rightTree.leftTree.value, is(2));
        assertThat(tree.getRoot().rightTree.rightTree.value, is(15));
        assertThat(tree.getSize(), is(5));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUseIterator() {
        tree.add(10);
        tree.add(2);
        tree.add(15);
        tree.add(-1);
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
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenChangeTreeShouldConcurrentModificationException() {
        Iterator<Integer> iterator = tree.iterator();
        tree.add(10);
        iterator.next();
    }

    @Test
    public void whenIteratorForEmptyTreeShouldReturnRootOnly() {
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(0));
        assertThat(iterator.hasNext(), is(false));
    }

}