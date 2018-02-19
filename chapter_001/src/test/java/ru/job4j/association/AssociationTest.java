package ru.job4j.association;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikoaly Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class AssociationTest {
    private Association association = new Association();

    @Test
    public void whenDimentionsEquals() {
        int[] firstArray = new int[] {1, 3, 5, 7, 9};
        int[] secondArray = new int[] {2, 4, 6, 8, 10};
        int[] resultArray = association.association(firstArray, secondArray);
        int[] expectedArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(resultArray, is(expectedArray));
    }

    @Test
    public void whenFirstArrayLarge() {
        int[] firstArray = new int[] {1, 3, 5, 7, 9, 11, 18};
        int[] secondArray = new int[] {3, 7, 7, 8, 10};
        int[] resultArray = association.association(firstArray, secondArray);
        int[] expectedArray = new int[] {1, 3, 3, 5, 7, 7, 7, 8, 9, 10, 11, 18};
        assertThat(resultArray, is(expectedArray));
    }

    @Test
    public void whenSecondArrayLarge() {
        int[] firstArray = new int[] {1, 8, 19, 20, 25};
        int[] secondArray = new int[] {2, 4, 9, 18, 20, 23, 24};
        int[] resultArray = association.association(firstArray, secondArray);
        int[] expectedArray = new int[] {1, 2, 4, 8, 9, 18, 19, 20, 20, 23, 24, 25};
        assertThat(resultArray, is(expectedArray));
    }
}