package ru.job4j.jobparser;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class InitSQL implements AutoCloseable {

    private final static Logger LOGGER = LogManager.getLogger("myLogger");
    private Connection conn;

    InitSQL(Properties config) {
        try {
            Class.forName(config.getProperty("jdbc.driver"));
            conn = DriverManager.getConnection(config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password"));
            LOGGER.info("The connection to the database was successful");

            DatabaseMetaData dm = conn.getMetaData();
            ResultSet rs = dm.getTables(null, null, "vacancy", null);
            if (!rs.next()) {
                Statement st  = conn.createStatement();
                st.execute("Create table vacancy ("
                        + "id serial primary key,"
                        + "reference character(200),"
                        + "description character(500),"
                        + "created timestamp"
                        + ");");
                LOGGER.info("created table 'vacancy'");
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error connecting to database", e);
        }
    }

    public void add(Vacancy vacancy) {
        try (PreparedStatement pst = conn.prepareStatement(
                "Insert into vacancy (reference, description, created) VALUES (?, ?, ?);");) {
            pst.setString(1, vacancy.getUrl());
            pst.setString(2, vacancy.getDesc());
            pst.setTimestamp(3, java.sql.Timestamp.valueOf(vacancy.getData()));
            pst.executeUpdate();
            LOGGER.debug("The object " + vacancy.getUrl() + " was added to the database");
        } catch (SQLException e) {
            LOGGER.error("Error adding item to database", e);
        }
    }

    //возвращает дату последнего добавленного элемента
    public LocalDateTime getLastDataTime() {

        LocalDateTime resultDateTime = null;
        try (Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select max(created) from vacancy;")) {

            if (rs.next()) {
                if (rs.getTimestamp(1) != null) {
                    resultDateTime = rs.getTimestamp(1).toLocalDateTime();
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return resultDateTime;
    }

    public void removeDuplicates() {
        try (Statement st = conn.createStatement()) {
            st.execute("delete from vacancy where id not in (select max(id) from vacancy group by description);");
            LOGGER.info("Replicated duplicate removal in the database");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
        LOGGER.info("Connection to the database is closed");
    }
}
