package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class UserConvertTest {
    private UserConvert userConvert = new UserConvert();

    @Test
    public void whenUserProcess() {
        User user1 = new User(1, "Nikolay", "Engels");
        User user2 = new User(5, "Petr", "Brynsk");
        User user3 = new User(4, "Igor", "Moscow");

        ArrayList<User> list = new ArrayList<>(Arrays.asList(user1, user2, user3));

        HashMap<Integer, User> result = userConvert.process(list);

        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, user1);
        expected.put(5, user2);
        expected.put(4, user3);

        assertThat(result, is(expected));
    }
}