package ru.job4j.sort;

import java.util.*;

public class SortUser {
    public Set<User> sort(List<User> userList) {
        return new TreeSet<>(userList);
    }
}
