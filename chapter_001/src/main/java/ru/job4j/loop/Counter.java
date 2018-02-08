package ru.job4j.loop;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Counter {
    /**
     * возвращает сумму всех четных чисел от start до finish
     * @param start Начало диапазона
     * @param finish Конец диапазона
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result += i;
            }
        }
        return result;
    }
}
