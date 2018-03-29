package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class MatrixIterator implements Iterator<Integer> {

    private int[] massiv;
    private int position = 0;

    MatrixIterator(final int[][] massiv) {
        int lenghtMas = 0;
        for (int[] subMas : massiv) {
            lenghtMas += subMas.length;
        }
        int[] result = new int[lenghtMas];
        int index = 0;
        for (int[] subMas : massiv) {
            for (int value : subMas) {
                result[index++] = value;
            }
        }
        this.massiv = result;

    }

    @Override
    public boolean hasNext() {
        return (position < massiv.length);
    }

    @Override
    public Integer next() {
        if (position >= massiv.length) {
            throw new NoSuchElementException();
        }
        return massiv[position++];
    }
}