package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WithEqualsAndHashCodeTest {

    private Map<User, Object> map = new HashMap<>();

    @Test
    public void whenAddSameElementsShouldAddOneElement() {
        User us1 = new User("Igor", new GregorianCalendar(1984, 1, 12), 1);
        User us2 = new User("Igor", new GregorianCalendar(1984, 1, 12), 1);
        map.put(us1, "Igor_1");
        map.put(us2, "Igor_2");
        System.out.println(map);
        assertThat(map.size(), is(1));
    }

}