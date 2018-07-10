package ru.job4j.jobparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
class InitSQL implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(InitSQL.class);
    private static final Marker INFO_MARKER = MarkerManager.getMarker("info");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("error");
    private Connection conn;

    /**
     * Инициализирует базу данных, используя входящие параметры конфигурации
     * @param config файл конфигурации, содержащий настройки подключения к базе данных
     */
    InitSQL(Properties config) {
        try {
            Class.forName(config.getProperty("jdbc.driver"));
            conn = DriverManager.getConnection(config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password"));
            LOGGER.info(INFO_MARKER, "The connection to the database was successful");
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
                LOGGER.info(INFO_MARKER, "created table 'vacancy'");
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(ERROR_MARKER, "Error connecting to database", e);
        }
    }

    /**
     * Добавляет указанный объект в базу данных
     * @param vacancy Вакансия
     * @see Vacancy#Vacancy(String, String, LocalDateTime)
     */
    public void add(Vacancy vacancy) {
        try (PreparedStatement pst = conn.prepareStatement(
                "Insert into vacancy (reference, description, created) VALUES (?, ?, ?);");) {
            pst.setString(1, vacancy.getUrl());
            pst.setString(2, vacancy.getDesc());
            pst.setTimestamp(3, java.sql.Timestamp.valueOf(vacancy.getData()));
            pst.executeUpdate();
            LOGGER.info(INFO_MARKER, "The object " + vacancy.getUrl() + " was added to the database");
        } catch (SQLException e) {
            LOGGER.error(ERROR_MARKER, "Error adding item to database", e);
        }
    }

    /**
     * Возвращает дату последней загруженной в базу данных вакансии
     * @return Дата последней загруженной вакансии в формате LocalDateTime
     */
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
            LOGGER.error(ERROR_MARKER, "SQLException", e);
        }
        return resultDateTime;
    }

    /**
     * Удаляет дубликаты записей в базе данных
     */
    public void removeDuplicates() {
        try (Statement st = conn.createStatement()) {
            st.execute("delete from vacancy where id not in (select max(id) from vacancy group by description);");
            LOGGER.info(INFO_MARKER, "Replicated duplicate removal in the database");
        } catch (SQLException e) {
            LOGGER.error(ERROR_MARKER, "SQLException", e);
        }
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
        LOGGER.info(INFO_MARKER, "Connection to the database is closed");
    }
}
