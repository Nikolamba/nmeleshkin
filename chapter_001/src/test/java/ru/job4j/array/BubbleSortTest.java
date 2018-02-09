package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSortTest {
    @Test
    //когда целевой массив содержит 10 элементов
    public void whenArrayWith10Element() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] resMas = new int[]{10, 5, 1, 3, 2, 6, 5, 9, 8, 4};
        resMas = bubbleSort.sort(resMas);
        int[] expectedMas = new int[]{1, 2, 3, 4, 5, 5, 6, 8, 9, 10};
        assertThat(resMas, is(expectedMas));
    }

}