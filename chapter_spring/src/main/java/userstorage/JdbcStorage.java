package userstorage;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcStorage implements Storage, Closeable {

    private Connection connection;

    JdbcStorage(Properties properties) {
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("userpass"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        try (PreparedStatement ps = connection.prepareStatement("insert into users values (DEFAULT, ?)")) {
            ps.setString(1, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
