package userstorage;

import java.util.Properties;

public class ImportUser {

    private ImportUser() { }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("driver", "org.postgresql.Driver");
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/userstorage");
        properties.setProperty("username", "postgres");
        properties.setProperty("userpass", "123456");

        UserStorage userStorage = new UserStorage(new JdbcStorage(properties));
        userStorage.add(new User("Nikolay"));
    }
}
