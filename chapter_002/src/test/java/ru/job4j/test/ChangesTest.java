package ru.job4j.test;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author Nikolay Meleshkin(sol.of.f@mail.ru)
 * @version 0.1
 */
public class ChangesTest {

    @Test
    public void whenChanges() {
        Changes changes = new Changes();
        int[] result = changes.changes(100, 65);
        int[] expected = new int[]{10, 10, 10, 5};
        assertThat(result, is(expected));
    }

    @Test
    public void whenChanges2() {
        Changes changes = new Changes();
        int[] result = changes.changes(100, 62);
        int[] expected = new int[]{10, 10, 10, 5, 2, 1};
        assertThat(result, is(expected));
    }
}