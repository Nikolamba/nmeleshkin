package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class FindLoopTest {
    private int[] data = new int[]{1, 5, 3, 8, 14, 18, 7};
    private FindLoop findLoop = new FindLoop();

    @Test
    //когда элемент есть в списке
    public void whenElementInList() {
        int element = findLoop.indexOf(data, 8);
        //ожидаем индекс найденного элемента = 3
        int expected = 3;
        assertThat(element, is(expected));
    }

    @Test
    //когда элемента нет в списке
    public void whenElementIsNotList() {
        int element = findLoop.indexOf(data, 10);
        //ожидаем, что элемента в списке нет, индекс -1
        int expected = -1;
        assertThat(element, is(expected));
    }
}