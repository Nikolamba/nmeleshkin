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
<<<<<<< HEAD

=======
        if (n == 0) {
            return 1;
        }
>>>>>>> origin/master
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
