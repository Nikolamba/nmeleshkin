package ru.job4j.music.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.music.DBConnection;
import ru.job4j.music.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DaoRole implements Dao<Role> {

    private final BasicDataSource source = DBConnection.getInstance().getSource();
    private static final DaoRole INSTANCE = new DaoRole();

    private DaoRole() {
        //this.create(new Role(1, "Admin"));
        //this.create(new Role(2, "Mandator"));
        //this.create(new Role(3, "User"));
    }

    public static DaoRole getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean create(Role obj) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO role VALUES (?, ?)")) {
            st.setInt(1, obj.getId());
            st.setString(2, obj.getRole());
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
             PreparedStatement st = connection.prepareStatement("UPDATE role SET role.type = ? WHERE id = ?")) {
            st.setString(1, data.getProperty("role"));
            st.setInt(2, id);
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
             PreparedStatement st = connection.prepareStatement("DELETE FROM role WHERE id = ?")) {
            st.setInt(1, id);
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public Role findById(int id) {
        Role result = null;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM role WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = new Role(rs.getInt("id"), rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Role> findAll() {
        List<Role> result = new ArrayList<>();
        try (Connection connection = source.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM role");
            while (rs.next()) {
                result.add(new Role(rs.getInt("id"), rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
