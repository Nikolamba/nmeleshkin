package ru.job4j.music.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.music.DBConnection;
import ru.job4j.music.models.MusicType;
import ru.job4j.music.models.User;
import ru.job4j.music.models.UsersMusicType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DaoUsersMusicType implements Dao<UsersMusicType> {

    private final BasicDataSource source = DBConnection.getInstance().getSource();
    private static final DaoUsersMusicType INSTANCE = new DaoUsersMusicType();

    private DaoUsersMusicType() { }

    public static DaoUsersMusicType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean create(UsersMusicType obj) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users_music_type VALUES (DEFAULT, ?, ?)")) {
            st.setInt(1, obj.getUserId());
            st.setInt(2, obj.getMusicTypeId());
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
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE users_music_type SET id_user = ?, id_music_type = ? WHERE id = ?")) {
            st.setInt(1, Integer.valueOf(data.getProperty("idUser")));
            st.setInt(2, Integer.valueOf(data.getProperty("idMusicType")));
            st.setInt(3, id);
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
             PreparedStatement st = connection.prepareStatement("DELETE FROM users_music_type WHERE id = ?")) {
            st.setInt(1, id);
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public UsersMusicType findById(int id) {
        UsersMusicType result = null;
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users_music_type WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = new UsersMusicType(rs.getInt("id"), rs.getInt("id_user"),
                        rs.getInt("id_music_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<UsersMusicType> findAll() {
        List<UsersMusicType> result = new ArrayList<>();
        try (Connection connection = source.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM music_type");
            while (rs.next()) {
                result.add(new UsersMusicType(rs.getInt("id"), rs.getInt("id_user"),
                        rs.getInt("id_music_type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<UsersMusicType> findAllForUser(User user) {
        List<UsersMusicType> result = new ArrayList<>();
        try (Connection connection = source.getConnection();
            PreparedStatement st = connection.prepareStatement("select umt.id, umt.id_user, umt.id_music_type "
                    + "from users_music_type as umt "
                    + "join users on umt.id_user = users.id "
                    + "join music_type on umt.id_music_type = music_type.id "
                    + "where users.id = ?;")) {
            st.setInt(1, user.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new UsersMusicType(rs.getInt("id"), rs.getInt("id_user"),
                        rs.getInt("id_music_type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<UsersMusicType> findAllForMusicType(MusicType musicType) {
        List<UsersMusicType> result = new ArrayList<>();
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("select umt.id, umt.id_user, umt.id_music_type "
                     + "from users_music_type as umt "
                     + "join users on umt.id_user = users.id "
                     + "join music_type on umt.id_music_type = music_type.id "
                     + "where music_type.id = ?;")) {
            st.setInt(1, musicType.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new UsersMusicType(rs.getInt("id"), rs.getInt("id_user"),
                        rs.getInt("id_music_type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
