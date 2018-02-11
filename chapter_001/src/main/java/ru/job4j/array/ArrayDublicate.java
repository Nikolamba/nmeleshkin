package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Nikolay Meleshkin(sol.of.f@mail.ru
 */
public class ArrayDublicate {
    /**
     * функция, удаляющая дубликаты в указанном массиве
     * @param array целевой массив
     * @return возвращает ссылку на новый массив, не содержащий дубликатов
     */
    public String[] remove(String[] array) {
        int lenght = array.length;
        for (int out = 0; out < lenght - 1; out++) {
            for (int in = out + 1; in < lenght; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[lenght - 1];
                    lenght--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, lenght);
    }
}
