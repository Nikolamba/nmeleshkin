package ru.job4j.max;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Max {
    /**
     * Возвращает максимум из двух чисел
     * @param first Первое число
     * @param second Второе число
     */
    public static int max(int first, int second) {
        return (first >= second ? first : second);
    }
    /**
     * Возвращает максимум из трех чисел
     * @param first Первое число
     * @param second Второе число
     * @param third Третье число
     */
    public static int max(int first, int second, int third) {
        int result = max(first, second);
        result = max(result, third);
        return result;
    }
}
