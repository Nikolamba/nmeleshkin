package ru.job4j.convert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ConvertList {
    /**
     * функция конвертирует указанный массив в List
     * @param array конвертируемый массив
     * @return возвращает List
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new LinkedList<>();
        Arrays.stream(array).forEach(arr -> Arrays.stream(arr).forEach(result::add));
        return result;
    }

    /**
     * конвертирует указанную коллекцию в двумерный массив
     * @param list список для конвертации
     * @param rows количество рядов в двумерном массиве
     * @return двумерный массив
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] result = new int[rows][(list.size() / rows) + 1];
        Iterator iterator = list.iterator();

        for (int[] row : result) {
            for (int i = 0; i < rows; i++) {
                if (iterator.hasNext()) {
                    row[i] = (Integer) iterator.next();
                }
            }
        }
        return result;
    }

    /**
     * ковертирует коллекцию массивов в коллекцию примитивов
     * @param list коллекция для конвертации
     * @return возвращает List
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new LinkedList<>();
        list.forEach(arr -> Arrays.stream(arr).forEach(result::add));
        return result;
    }
}
