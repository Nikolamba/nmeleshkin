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
    private static DbStore instance = new DbStore();

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
        try (Connection connection = SOURCE.getConnection()) {
            DatabaseMetaData dm = connection.getMetaData();
            ResultSet rs = dm.getTables(null, null, "users", null);
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
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static DbStore getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.prepareStatement("Insert into users values (?, ?, ?, ?, ?);")) {
            ((PreparedStatement) st).setInt(1, user.getId());
            ((PreparedStatement) st).setString(2, user.getName());
            ((PreparedStatement) st).setString(3, user.getLogin());
            ((PreparedStatement) st).setString(4, user.getEmail());
            ((PreparedStatement) st).setDate(5, java.sql.Date.valueOf(user.getCreateDate()));
            ((PreparedStatement) st).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, String newName) {
        try (Connection connection = SOURCE.getConnection();
            Statement st = connection.prepareStatement("update users set name = ? where id = ?;")) {
            ((PreparedStatement) st).setString(1, newName);
            ((PreparedStatement) st).setInt(2, id);
            ((PreparedStatement) st).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = SOURCE.getConnection();
            Statement st = connection.prepareStatement("delete from users where id = ?;")) {
            ((PreparedStatement) st).setInt(1, user.getId());
            ((PreparedStatement) st).execute();
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
            Statement st = connection.prepareStatement("select * from users where id = ?;")) {
            ((PreparedStatement) st).setInt(1, id);
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
