package ru.job4j.sort;

import java.util.*;

/**
 * Класс, содержащий методы сортировки для класса User
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class SortUser {
    /**
     * возвращает TreeSet пользователей, отсортированных по возрасту в порядке возрастания
     * @param userList список пользователей для сортировки
     * @return возвращает упорядоченное по возрасту множество пользователей
     */
    public Set<User> sort(List<User> userList) {
        return new TreeSet<>(userList);
    }

    /**
     * Сортирует пользователей по длине имени
     * @param list входящий массив для сортировки
     * @return возвращает массив отсортированных пользователей
     */
    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return list;
    }

    /**
     * Сортирует пользователей по всем полям, сначала по имени в лексографическом порядке,
     * затем по возрасту
     * @param list входящий список для сортировки
     * @return возвращает список отсортированных пользователей
     */
    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                if (result == 0) {
                    result = o1.compareTo(o2);
                }
                return result;
            }
        });
        return list;
    }
}
