package ru.job4j.sort;

import java.util.Comparator;

/**
 * Класс для сравнения строковых массивов
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int result = 0;
        String minString = (left.length() < right.length()) ? left : right;

        for (int i = 0; i < minString.length(); i++) {
            if (left.charAt(i) < right.charAt(i)) {
                result = -1;
            } else if (left.charAt(i) > right.charAt(i)) {
                result = 1;
            }
        }
        if (result == 0) {
            result = left.length() - right.length();
        }
        return result;
    }
}
