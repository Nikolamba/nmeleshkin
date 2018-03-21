package ru.job4j.association;

/**
 * класс для объединения двух массивов, элементы в которых упорядочены
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Association {
    /**
     * функция, объединяющая 2 входящих массива
     * @param firstArray первый массив, элементы в котором упорядочены
     * @param secondArray второй массив, элементы в котором упорядочены
     * @return возвращает ссылку на новый массив, элементы в котором также упорядочены
     */
    public int[] association(int[] firstArray, int[] secondArray) {
        int lenght = firstArray.length + secondArray.length;
        int[] resultArray = new int[lenght];
        int counter = 0;
        int first = 0, second = 0;
        while (counter != lenght) {
                if (firstArray[first] < secondArray[second]) {
                    resultArray[counter] = firstArray[first];
                    if (first == firstArray.length - 1) {
                        firstArray[first] = Integer.MAX_VALUE;
                    } else {
                        first++;
                    }
                    counter++;
                } else {
                    resultArray[counter] = secondArray[second];
                    if (second == secondArray.length - 1) {
                        secondArray[second] = Integer.MAX_VALUE;
                    } else {
                        second++;
                    }
                    counter++;
                    }
                }
        return resultArray;
    }

}
