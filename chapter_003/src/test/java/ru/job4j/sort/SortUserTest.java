package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortUserTest {
    private SortUser sortUser = new SortUser();

    @Test
    public void whenUseSort() {
        User us1 = new User("Nikolay", 33);
        User us2 = new User("Igor", 27);
        User us3 = new User("Aleksandr", 16);
        User us4 = new User("Petr", 38);
        List<User> list = new ArrayList<>(Arrays.asList(us1, us2, us3, us4));
        Set<User> result = sortUser.sort(list);

        Set<User> expected = new TreeSet<>(Arrays.asList(us1, us3, us2, us4));

        assertThat(result, is(expected));
    }
}