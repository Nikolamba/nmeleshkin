package ru.job4j.array;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class VerifyWords {
    /**
     * Функция проверяет, содержится ли второе слово в первом
     * @param origin первое слово
     * @param sub второе слово
     * @return возвращает true, если второе слово присутствует в первом
     */
    boolean contains(String origin, String sub) {
        boolean result = false;
        char[] originArray = stringToArray(origin);
        char[] subArray = stringToArray(sub);
        int counter = 0;
        for (char origSym : originArray) {
            if (origSym == subArray[counter]) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == subArray.length) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * функция преобразует строковый литерал в массив символов
     * @param str строковый литерал
     * @return ссылку на массив символов
     */
    private char[] stringToArray(String str) {
        char[] strArray = new char[str.length()];
        for (int index = 0; index < str.length(); index++) {
            strArray[index] = str.charAt(index);
        }
        return strArray;
    }
}
