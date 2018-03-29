package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class EvenIterator implements Iterator<Integer> {

    private int[] massiv;
    private int position;

    EvenIterator(int[] massiv) {
        this.massiv = massiv;
        this.position = 0;
    }

    private Integer getNextEven() {
        Integer result = null;
        for (int i = this.position; i < massiv.length; i++) {
            if (massiv[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
       return (this.getNextEven() != null);
    }

    @Override
    public Integer next() {
        if (this.getNextEven() == null) {
            throw new NoSuchElementException();
        }
        this.position = this.getNextEven();
        return this.massiv[this.position++];
    }
}
