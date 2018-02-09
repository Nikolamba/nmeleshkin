package ru.job4j.array;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Turn {
    /**
     * функция переворачивает массив, заданный в параметре
     * и возвращает новый массив
     * @param array целевой массив
     * @return возвращает перевернутый массив
     */
    public int[] back(int[] array) {
        //временная переменная для хранения элементов массива
        int var;
        for (int i = 0, j = array.length - 1; i < (array.length / 2); i++, j--) {
            //в целевом массиве меняем элементы местами
            var = array[i];
            array[i] = array[j];
            array[j] = var;
        }
        return array;
    }
}
