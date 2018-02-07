package ru.job4j.max;
/**
 * Возвращает максимум из двух чисел.
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Max {
    /**
     * @param first Первое число
     * @param second Второе число
     */
    public static int max(int first, int second) {
        return (first >= second ? first : second);
    }
}
