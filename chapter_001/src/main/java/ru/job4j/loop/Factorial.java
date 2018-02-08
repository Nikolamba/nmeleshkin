package ru.job4j.loop;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Factorial {
    /**
     * метод подсчитывает факториал указанного числа
     */
    public int calc(int n) {
        int result = 1;

        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
