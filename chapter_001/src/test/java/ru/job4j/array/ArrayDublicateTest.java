package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru
 * @version $Id#
 * @since 0.1
 */
public class ArrayDublicateTest {
    private ArrayDublicate arrayDublicate = new ArrayDublicate();

    @Test
    public void whenArrayWithoutDublicates() {
        String[] strings = new String[]{"Nick", "Liza", "Masha", "Misha", "Viktor"};
        String[] dataString = arrayDublicate.remove(strings);
        String[] expectedArray = new String[]{"Nick", "Liza", "Masha", "Misha", "Viktor"};
        assertThat(dataString, arrayContainingInAnyOrder(expectedArray));
    }

    @Test
    public void whenArrayWithDublicates() {
        String[] strings = new String[]{"Nick", "Liza", "Masha", "Masha", "Misha", "Viktor", "Liza"};
        String[] dataString = arrayDublicate.remove(strings);
        String[] expectedArray = new String[]{"Nick", "Liza", "Masha", "Misha", "Viktor"};
        assertThat(dataString, arrayContainingInAnyOrder(expectedArray));
    }

}