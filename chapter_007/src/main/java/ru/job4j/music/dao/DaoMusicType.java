package ru.job4j.music.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.music.DBConnection;
import ru.job4j.music.models.MusicType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DaoMusicType implements Dao<MusicType> {

    private final BasicDataSource source = DBConnection.getInstance().getSource();
    private static final DaoMusicType INSTANCE = new DaoMusicType();

    private DaoMusicType() {
        //this.create(new MusicType(1, "Rock"));
        //this.create(new MusicType(2, "Pap"));
        //this.create(new MusicType(3, "Classic"));
    }

    public static DaoMusicType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean create(MusicType obj) {
        boolean result = false;
        try (Connection connection = source.getConnection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO music_type VALUES (?, ?)")) {
            st.setInt(1, obj.getId());
            st.setString(2, obj.getType());
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
            PreparedStatement st = connection.prepareStatement("UPDATE music_type SET type = ? WHERE id = ?")) {
            st.setString(1, data.getProperty("type"));
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
            PreparedStatement st = connection.prepareStatement("DELETE FROM music_type WHERE id = ?")) {
            st.setInt(1, id);
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public MusicType findById(int id) {
        MusicType result = null;
        try (Connection connection = source.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM music_type WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = new MusicType(rs.getInt("id"), rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<MusicType> findAll() {
        List<MusicType> result = new ArrayList<>();
        try (Connection connection = source.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM music_type");
            while (rs.next()) {
                result.add(new MusicType(rs.getInt("id"), rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
