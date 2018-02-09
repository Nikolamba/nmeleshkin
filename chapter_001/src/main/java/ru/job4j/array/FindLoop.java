package ru.job4j.array;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class FindLoop {
    /**
     * метод возвращает индекс найденного элемента или -1
     * @param data целевой массив, в котором производится поиск
     * @param element целочисленное значение, индекс которого нужно найти
     *                в целевом массиве
     * @return возвращает индекс найденного элемента или -1,
     *         если элемент в списке не найден
     */
    public int indexOf(int[] data, int element) {
        int result = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int i = 0; i < data.length; i++) {
            //если элемент найден в массиве, возвращаем индекс этого элемента
            if (data[i] == element) {
                result = i;
                break;
            }
        }
        return result;
    }
}
