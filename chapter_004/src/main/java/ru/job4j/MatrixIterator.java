package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */

public class MatrixIterator implements Iterator<Integer> {

    private int[][] array;
    private int position = 0;
    private int subPostion = 0;

    MatrixIterator(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result;
        if (array.length == 0) {
            result = false;
        } else {
            result = ((position < array.length - 1) || (subPostion != array[position].length));
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (subPostion == array[position].length) {
            position++;
            subPostion = 0;
        }
        return array[position][subPostion++];
    }
}