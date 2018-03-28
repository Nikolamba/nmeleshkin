package ru.job4j.test;

/**
 * @author Nikolay Meleshkin(sol.of.f@mail.ru)
 * @version 0.1
 */
public class Changes {
    private int[] coin = new int[]{1, 2, 5, 10};

    int[] changes(int value, int price) {
        int oddMoney = value - price;
        int[] res = new int[oddMoney];
        int index = 0;

        for (int i = coin.length - 1; i >= 0; i--) {
            while(oddMoney / coin[i] >= 1) {
                res[index++] = coin[i];
                oddMoney -= coin[i];
            }
        }
        int[] result = new int[index];
        System.arraycopy(res, 0, result, 0, index);
        return result;
    }
}
