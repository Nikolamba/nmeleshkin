package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class PrimeIterator implements Iterator<Integer> {
    private int[] massiv;
    private int position;

    PrimeIterator(final int[] massiv) {
        this.massiv = massiv;
        this.position = 0;
    }

    private Integer getNextPrime() {
        Integer result = null;
        for (int i = this.position; i < massiv.length; i++) {
            if (isPrime(massiv[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    private boolean isPrime(int value) {
        boolean result = true;
        if (value == 1) {
            return false;
        }
        for (int i = 2; i < value; i++) {
            if (value % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return (this.getNextPrime() != null);
    }

    @Override
    public Integer next() {
        if (this.getNextPrime() == null) {
            throw new NoSuchElementException();
        }
        this.position = this.getNextPrime();
        return this.massiv[this.position++];
    }
}
