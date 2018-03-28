package ru.job4j.test;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DepartmentsTest {
    private Departments dep = new Departments();

    @Test
    public void whenUseSortAscending() {
        String[] expected = new String[] {"K1\\", "K1\\SK1\\", "K1\\SK1\\SSK1\\", "K1\\SK1\\SSK2\\", "K1\\SK2\\",
                "K2\\", "K2\\SK1\\", "K2\\SK1\\SSK1\\", "K2\\SK1\\SSK2\\"};
        String[] result = dep.sortAscending();
        assertThat(result, is(expected));
    }

    @Test
    public void whenUseSortDecreasing() {
        String[] expected = new String[] {"K2\\", "K2\\SK1\\", "K2\\SK1\\SSK2\\", "K2\\SK1\\SSK1\\", "K1\\",
                "K1\\SK2\\", "K1\\SK1\\", "K1\\SK1\\SSK2\\", "K1\\SK1\\SSK1\\"};
        String[] result = dep.sortDecreasing();
        assertThat(result, is(expected));
    }
}