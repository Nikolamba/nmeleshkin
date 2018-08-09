package ru.job4j.http;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class DbStore implements Store<User> {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("123456");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try (Connection connection = SOURCE.getConnection();
            ResultSet rs = connection.getMetaData().getTables(
                    null, null, "users", null)) {
            if (!rs.next()) {
                Statement st = connection.createStatement();
                st.execute("create table users ("
                        + "id integer primary key,"
                        + "name character(50),"
                        + "login character(100),"
                        + "email character(100),"
                        + "created date"
                        + ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("Insert into users values (?, ?, ?, ?, ?);")) {
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setDate(5, java.sql.Date.valueOf(user.getCreateDate()));
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, String newName) {
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement st = connection.prepareStatement("update users set name = ? where id = ?;")) {
            st.setString(1, newName);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement st = connection.prepareStatement("delete from users where id = ?;")) {
            st.setInt(1, user.getId());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from users;");
            while (rs.next()) {
                resultList.add(new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("login"), rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from users where id = ?;")) {
            st.setInt(1, id);
            ResultSet rs = ((PreparedStatement) st).executeQuery();
            if (rs.next()) {
                result = new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("login"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
