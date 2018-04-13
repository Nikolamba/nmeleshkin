package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class SimpleHashSetTest {

    private SimpleHashSet<Integer> hashSet = new SimpleHashSet<>();

    @Before
    public void setUp() {
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
    }

    @Test
    public void whenAddSameElementsThenSizeDoesNotChange() {
        hashSet.add(1);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(5);
        assertThat(hashSet.size(), is(5));
    }

    @Test
    public void whenElementIsInSetThenContainsIsTrueElseFalse() {
        assertThat(hashSet.contains(2), is(true));
        assertThat(hashSet.contains(4), is(false));
    }

    @Test
    public void whenRemoveElementThenContainsIsFalse() {
        assertThat(hashSet.remove(2), is(true));
        assertThat(hashSet.contains(2), is(false));
    }

}