package ru.job4j.sort;

import java.util.Iterator;
import java.util.Objects;

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int compareTo(User user)   {

        int result = Integer.compare(this.age, user.age);
        result = (result == 0) ? result : this.name.compareTo(user.name);

        return result;
    }

    public String toString() {
        return (name + " " + age);
    }
}
