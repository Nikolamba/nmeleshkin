package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class TurnTest {
    private Turn turn = new Turn();

    @Test
    //когда в целевом массиве четное количество элементов
    public void whenEvenNumbersElement() {
        int[] evenMas = new int[]{1, 2, 3, 4, 5, 6};
        evenMas = turn.back(evenMas);
        int[] expectedMas = new int[]{6, 5, 4, 3, 2, 1};
        assertThat(evenMas, is(expectedMas));
    }

    @Test
    //когда в целевом массиве нечетное количество элементов
    public void whenOddNumbersElement() {
        int[] oddMas = new int[]{1, 2, 3, 4, 5};
        oddMas = turn.back(oddMas);
        int[] expectedMas = new int[]{5, 4, 3, 2, 1};
        assertThat(oddMas, is(expectedMas));
    }

}