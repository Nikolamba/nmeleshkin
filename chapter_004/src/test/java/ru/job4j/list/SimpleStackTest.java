package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    private SimpleStack<Integer> stack = new SimpleStack<>();

    @Test
    public void whenUsePushElementsThenPoolElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.pool(), is(3));
        assertThat(stack.pool(), is(2));
        stack.push(4);
        assertThat(stack.pool(), is(4));
        assertThat(stack.pool(), is(1));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenPoolElementFromEmptyStack() {
        stack.push(1);
        assertThat(stack.pool(), is(1));
        stack.pool();
    }
}