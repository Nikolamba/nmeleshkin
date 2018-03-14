package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class UserConvert {
    /**
     * функция конвертирует коллекцию List<User> в HashMap<Integer, User>
     * @param list входящяя колеекция для конвертаци
     * @return HashMap<Integer, User>, где Integer - id User
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();

        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
