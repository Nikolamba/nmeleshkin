package ru.job4j.music.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.music.DBConnection;
import ru.job4j.music.models.Adress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DaoAdress implements Dao<Adress> {

    private final BasicDataSource source = DBConnection.getInstance().getSource();
    private static final DaoAdress INSTANCE = new DaoAdress();

    private DaoAdress() { }

    public static DaoAdress getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean create(Adress obj) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO adress VALUES (DEFAULT, ?, ?, ?, ?) RETURNING id")) {
            st.setInt(1, obj.getZipCode());
            st.setString(2, obj.getCity());
            st.setString(3, obj.getStreet());
            st.setInt(4, obj.getHouseNum());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                obj.setId(rs.getInt(1));
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean edit(int id, Properties data) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE adress SET "
                     + "zip_code = ?, city = ?, street = ?, house_number = ? WHERE id = ?")) {
            st.setString(1, data.getProperty("zipCode"));
            st.setString(2, data.getProperty("city"));
            st.setString(3, data.getProperty("street"));
            st.setInt(4, Integer.valueOf(data.getProperty("houseNum")));
            st.setInt(5, id);
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
             PreparedStatement st = connection.prepareStatement("DELETE FROM adress WHERE id = ?")) {
            st.setInt(1, id);
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public Adress findById(int id) {
        Adress result = null;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM adress WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = new Adress(rs.getInt("zip_code"),
                        rs.getString("city"), rs.getString("street"),
                        rs.getInt("house_number"));
                result.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Adress> findAll() {
        List<Adress> result = new ArrayList<>();
        try (Connection connection = source.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM adress");
            while (rs.next()) {
                Adress adress = new Adress(rs.getInt("zip_code"),
                        rs.getString("city"), rs.getString("street"),
                        rs.getInt("house_number"));
                adress.setId(rs.getInt("id"));
                result.add(adress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
