package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WithoutEqualsAndHashCodeTest {

    private Map<User, Object> map = new HashMap<>();

    @Test
    public void whenAddSameElementsShouldAddAllElements() {
        User us1 = new User("Igor", new GregorianCalendar(1984, 1, 12), 1);
        User us2 = new User("Igor", new GregorianCalendar(1984, 1, 12), 1);
        map.put(us1, "Igor");
        map.put(us2, "Igor");
        System.out.println(map);
        assertThat(map.size(), is(2));
    }

}