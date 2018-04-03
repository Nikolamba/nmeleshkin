package ru.job4j.test;

import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Departments {

    //сортировка по возрастанию
    public String[] sortAscending() {
        List<String> list = this.getDepartment();
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return list.toArray(new String[list.size()]);
    }

    //сортировка по убыванию
    public String[] sortDecreasing() {
        List<String> list = this.getDepartment();
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;
                String[] split1 = o1.split("\\\\");
                String[] split2 = o2.split("\\\\");
                if (split1[0].compareTo(split2[0]) == 0) {
                    result = Integer.compare(o1.length(), o2.length());
                    if (result == 0) {
                        result = -(o1.compareTo(o2));
                    }
                } else {
                    result = -(o1.compareTo(o2));
                }
                return result;
            }
        });
        return list.toArray(new String[list.size()]);
    }

    //формируем полный список департаментов
    private List<String> getDepartment() {
        String[] massiv = new String[] {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        List<String> list = new ArrayList<>();

        for (String string : massiv) {
            String[] subStrings = string.split("\\\\");
            String accum = "";
            for (String sub : subStrings) {
                accum = accum + sub + "\\";
                if (!list.contains(accum)) {
                    list.add(accum);

                }
            }
        }
        return list;
    }
}
