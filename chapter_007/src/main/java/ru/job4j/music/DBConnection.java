package ru.job4j.music;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DBConnection {

    private final BasicDataSource source = new BasicDataSource();
    private static final DBConnection INSTANCE = new DBConnection();

    private DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        source.setDriverClassName("org.postgresql.Driver");
        source.setUrl("jdbc:postgresql://localhost:5432/music");
        source.setUsername("postgres");
        source.setPassword("123456");
        source.setMinIdle(5);
        source.setMaxIdle(10);
        source.setMaxOpenPreparedStatements(100);

        try (Connection connection = source.getConnection();
             ResultSet rs = connection.getMetaData().getTables(
                     null, null, "adress", null);
             Statement st = connection.createStatement()) {
            if (!rs.next()) {
                st.execute("CREATE TABLE adress ("
                        + "id serial primary key,"
                        + "zip_code integer,"
                        + "city character(50),"
                        + "street character(50),"
                        + "house_number integer"
                        + ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = source.getConnection();
             ResultSet rs = connection.getMetaData().getTables(
                     null, null, "role", null);
             Statement st = connection.createStatement()) {
            if (!rs.next()) {
                st.execute("CREATE TABLE role ("
                        + "id integer primary key,"
                        + "type character(50)"
                        + ");");
                st.execute("INSERT INTO role VALUES (1, 'Admin')");
                st.execute("INSERT INTO role VALUES (2, 'Mandator')");
                st.execute("INSERT INTO role VALUES (3, 'User')");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = source.getConnection();
             ResultSet rs = connection.getMetaData().getTables(
                     null, null, "users", null);
             Statement st = connection.createStatement()) {
            if (!rs.next()) {
                st.execute("CREATE TABLE users ("
                        + "id integer primary key,"
                        + "name character(50),"
                        + "id_adress integer,"
                        + "id_role integer,"
                        + "FOREIGN KEY (id_adress) references adress(id),"
                        + "FOREIGN KEY (id_role) references role(id)"
                        + ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = source.getConnection();
             ResultSet rs = connection.getMetaData().getTables(
                     null, null, "music_type", null);
             Statement st = connection.createStatement()) {
            if (!rs.next()) {
                st.execute("CREATE TABLE music_type ("
                        + "id integer primary key,"
                        + "type character(50)"
                        + ");");
                st.execute("INSERT INTO music_type VALUES (1, 'Rock')");
                st.execute("INSERT INTO music_type VALUES (2, 'Rap')");
                st.execute("INSERT INTO music_type VALUES (3, 'Classic')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = source.getConnection();
             ResultSet rs = connection.getMetaData().getTables(
                     null, null, "users_music_type", null);
             Statement st = connection.createStatement()) {
            if (!rs.next()) {
                st.execute("CREATE TABLE users_music_type ("
                        + "id SERIAL primary key,"
                        + "id_user integer references users(id),"
                        + "id_music_type integer,"
                        + "FOREIGN KEY (id_user) references users(id),"
                        + "FOREIGN KEY (id_music_type) references music_type(id)"
                        + ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        return INSTANCE;
    }

    public BasicDataSource getSource() {
        return this.source;
    }
}
