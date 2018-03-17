package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
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

    @Test
    public void whenUseSortNameLength() {
        List<User> list = new ArrayList<>(Arrays.asList(new User("Nikolay", 33),
                new User("Nataliya", 27),
                new User("Aleksandr", 16),
                new User("Petr", 38)));
        List<User> result = sortUser.sortNameLength(list);

        assertThat(result.get(0).getName(), is("Petr"));
        assertThat(result.get(1).getName(), is("Nikolay"));
        assertThat(result.get(2).getName(), is("Nataliya"));
        assertThat(result.get(3).getName(), is("Aleksandr"));
    }

    @Test
    public void whenUseSortByAllFields() {
        List<User> list = new ArrayList<>(Arrays.asList(new User("Nikolay", 33),
                new User("Nataliya", 27),
                new User("Nikolay", 16),
                new User("Petr", 38)));
        List<User> result = sortUser.sortByAllFields(list);

        assertThat(result.get(0), is(new User("Nataliya", 27)));
        assertThat(result.get(1), is(new User("Nikolay", 16)));
        assertThat(result.get(2), is(new User("Nikolay", 33)));
        assertThat(result.get(3), is(new User("Petr", 38)));
    }
}