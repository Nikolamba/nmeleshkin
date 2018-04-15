package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Класс, описывающий пользователя
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, children, birthday);
    }
}
