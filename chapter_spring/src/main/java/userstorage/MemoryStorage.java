package userstorage;

import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

@Component
public class MemoryStorage implements Storage, Closeable {

    private Connection connection;

    public MemoryStorage() {
        String url = "jdbc:hsqldb:mem:testdb";
        String userName = "sa";
        String userPass = "";
        Statement st = null;
        try {
            connection = DriverManager.getConnection(url, userName, userPass);
            st = connection.createStatement();
            st.executeUpdate("CREATE TABLE users(id IDENTITY , name character(50));");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void add(User user) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES (DEFAULT , ?)")) {
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
