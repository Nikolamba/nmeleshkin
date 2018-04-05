package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */

public class MatrixIterator implements Iterator<Integer> {

    private int[][] massiv;
    private int position = 0;
    private int subPostion = 0;

    MatrixIterator(final int[][] massiv) {
        this.massiv = massiv;
    }

    @Override
    public boolean hasNext() {
        return ((position != massiv.length - 1)
                || (subPostion != massiv[position].length));
    }

    @Override
    public Integer next() {
        if (!hasNext() || massiv.length == 0) {
            throw new NoSuchElementException();
        }
        if (subPostion == massiv[position].length) {
            position++;
            subPostion = 0;
        }
        return massiv[position][subPostion++];
    }
}