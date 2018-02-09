package ru.job4j.array;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class BubbleSort {
    /**
     * функция сортирует массив алгоритмом пузырька
     * @param array целевой массив для сортировки
     * @return возвращает ссылку на отсортированный массив
     */
    public int[] sort(int[] array) {
        //временная переменная для хранения элементов массива
        int var;
        //максимальное число проходов для сортировки = длине массива
        for (int i = 0; i < array.length; i++) {
            //цикл, который попарно сравнивает элементы массива
            for (int j = 0; j < array.length - 1; j++) {
                //если 2 элемента не отсортированы, сортируем их
                if (array[j] > array[j + 1]) {
                    var = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = var;
                }
            }
        }
        return array;
    }
}
