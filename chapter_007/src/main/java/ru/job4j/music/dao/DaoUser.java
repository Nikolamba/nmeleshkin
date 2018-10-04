package ru.job4j.music.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.music.DBConnection;
import ru.job4j.music.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DaoUser implements Dao<User> {

    private final BasicDataSource source = DBConnection.getInstance().getSource();
    private static final DaoUser INSTANCE = new DaoUser();

    private DaoUser() { }

    public static DaoUser getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean create(User obj) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)")) {
            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());
            st.setInt(3, obj.getIdAdress());
            st.setInt(4, obj.getIdRole());
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean edit(int id, Properties data) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE users SET "
                     + "name = ?, id_adress = ?, id_role = ? WHERE id = ?")) {
            st.setString(1, data.getProperty("name"));
            st.setInt(2, Integer.valueOf(data.getProperty("idAdress")));
            st.setInt(3, Integer.valueOf(data.getProperty("idRole")));
            st.setInt(4, id);
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            st.setInt(1, id);
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = new User(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("id_adress"), rs.getInt("id_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = source.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                result.add(new User(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("id_adress"), rs.getInt("id_role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
