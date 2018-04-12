package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    private SimpleQueue<Integer> queue = new SimpleQueue<>();

    @Test
    public void whenUsePushElementsThenPoolElements() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.pool(), is(1));
        assertThat(queue.pool(), is(2));
        queue.push(4);
        assertThat(queue.pool(), is(3));
        assertThat(queue.pool(), is(4));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenPoolElementFromEmptyQueue() {
        queue.push(1);
        assertThat(queue.pool(), is(1));
        queue.pool();
    }

}