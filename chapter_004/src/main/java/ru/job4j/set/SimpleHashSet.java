package ru.job4j.set;

import java.util.Arrays;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <E> тип данных этого множества
 */
public class SimpleHashSet<E> {

    private final static int CAPACITY = 2;
    private int sizeArray = 1;
    private Object[] array;
    private int countElement;

    SimpleHashSet() {
        this.array = new Object[sizeArray];
    }

    public void add(E element) {
        int hashEl = countHash(element);
        if (array[hashEl] == null) {
            array[hashEl] = element;
            countElement++;
        } else if (array[hashEl] != element) {
            this.grow();
            add(element);
        }
    }

    public boolean contains(E element) {
        return (array[countHash(element)] == element);
    }

    public boolean remove(E element) {
        boolean result;
        if (this.contains(element)) {
            array[countHash(element)] = null;
            countElement--;
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public int size() {
        return countElement;
    }

    private int countHash(E element) {
        return element.hashCode() % sizeArray;
    }

    /**
     * увеличивает вдвое размер массива для хранения данных
     * старые данные заново хешируются и распределяются в массиве
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        sizeArray *= CAPACITY;

        Object[] arr = new Object[sizeArray];

        for (Object obj : array) {
             if (obj != null) {
                 arr[countHash((E) obj)] = obj;
             }
        }
        array = Arrays.copyOf(array, sizeArray);
        System.arraycopy(arr, 0, array, 0, sizeArray);
    }

}
