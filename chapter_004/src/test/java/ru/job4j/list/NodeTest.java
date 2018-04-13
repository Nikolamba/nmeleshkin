package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NodeTest {
    private Node<Integer> first = new Node<>(1);
    private Node<Integer> two = new Node<>(2);
    private Node<Integer> third = new Node<>(3);
    private Node<Integer> four = new Node<>(4);

    @Test
    public void whenNoCycleThenShouldReturnFalse() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;

        boolean expected = first.hasCycle(first);

        assertThat(expected, is(false));
    }

    @Test
    public void whenIsCycleOnFirstNodeWhenShouldReturnTrue() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        boolean expected = first.hasCycle(first);

        assertThat(expected, is(true));
    }

    @Test
    public void whenIsCycleInsideListShouldReturnTrue() {
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = first;

        boolean expected = first.hasCycle(first);

        assertThat(expected, is(true));
    }
}