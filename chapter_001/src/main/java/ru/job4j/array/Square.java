package ru.job4j.array;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Square {
    /**
     * метод возвращает массив индексов, возвещенных в квадрат
     * @param bound ограничение
     */
    public int[] calculate(int bound) {
        int[] mas = new int[bound];
        for (int i = 1; i <= mas.length; i++) {
            mas[i - 1] = i * i;
        }
        return mas;
    }
}
