package userstorage;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImportUserTest {

    private UserStorage userStorage = new UserStorage(new MemoryStorage());

    @Test
    public void whenAddUserShouldGetIt() {
        userStorage.add(new User("Nikolay"));

    }

}