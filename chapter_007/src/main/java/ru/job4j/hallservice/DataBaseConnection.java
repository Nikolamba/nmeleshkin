package ru.job4j.hallservice;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DataBaseConnection {

    private final BasicDataSource source = new BasicDataSource();
    private final static DataBaseConnection INSTANCE = new DataBaseConnection();

    private DataBaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        source.setDriverClassName("org.postgresql.Driver");
        source.setUrl("jdbc:postgresql://localhost:5432/hall_service");
        source.setUsername("postgres");
        source.setPassword("123456");
        source.setMinIdle(5);
        source.setMaxIdle(10);
        source.setMaxOpenPreparedStatements(100);

        try (Connection connection = source.getConnection();
             ResultSet rs = connection.getMetaData().getTables(
                     null, null, "account", null);
             Statement st = connection.createStatement()) {
            if (!rs.next()) {
                st.execute("CREATE TABLE account ("
                        + "id serial primary key,"
                        + "name character(100),"
                        + "phone_number character(20)"
                        + ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = source.getConnection();
             ResultSet rs = connection.getMetaData().getTables(
                     null, null, "halls", null);
             Statement st = connection.createStatement();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO halls VALUES (?, ?, NULL)")) {
            if (!rs.next()) {
                st.execute("CREATE TABLE halls ("
                        + "row INTEGER,"
                        + "place INTEGER,"
                        + "account_id INTEGER default null,"
                        + "FOREIGN KEY (account_id)references account(id),"
                        + "PRIMARY KEY (row, place)"
                        + ");");
                for (int row = 1; row < 4; row++) {
                    for (int place = 1; place < 4; place++) {
                        ps.setInt(1, row);
                        ps.setInt(2, place);
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DataBaseConnection getInstance() {
        return INSTANCE;
    }
    public BasicDataSource getSource() {
        return this.source;
    }
}
