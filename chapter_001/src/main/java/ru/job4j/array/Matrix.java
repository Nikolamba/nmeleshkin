package ru.job4j.array;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Matrix {
    /**
     * функция, возвращающая матрицу таблицы умножения, указанного размера
     * @param size размер матрицы
     * @return возвращает ссылку на матрицу
     */
    public int[][] multiple(int size) {
        //матрица для заполнения данными
        int[][] data = new int[size][size];
        //цикл для заполнения матрицы
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = (i + 1) * (j + 1);
            }
        }
        return data;
    }
}
