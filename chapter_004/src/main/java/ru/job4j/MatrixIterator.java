package ru.job4j;

import java.util.Iterator;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class MatrixIterator implements Iterator<Integer> {

    private Integer[] massiv;
    private int position = 0;

    MatrixIterator(int[][] massiv) {
        int lenghtMas = 0;
        for (int[] subMas : massiv) {
            lenghtMas += subMas.length;
        }
        Integer[] result = new Integer[lenghtMas];
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
        return (massiv.length > position);
    }

    @Override
    public Integer next() {
        return massiv[position++];
    }
}
