package ru.job4j.tracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class InitSql {

    private Connection conn;

    InitSql(String url, String userName, String userPass) {
        try {
            conn = DriverManager.getConnection(url, userName, userPass);
            DatabaseMetaData dm = conn.getMetaData();
            ResultSet rs = dm.getTables(null, null, "items", null);
            if (!rs.next()) {
                Statement st  = conn.createStatement();
                st.execute("Create table Items ("
                        + "id character(20) primary key,"
                        + "name character(100),"
                        + "description character(500),"
                        + "created bigint"
                        + ");");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void add(Item item) {
        try (PreparedStatement pst = conn.prepareStatement("Insert into items VALUES (? ,?, ?, ?);");) {
            pst.setString(1, this.generateID());
            pst.setString(2, item.getName());
            pst.setString(3, item.getDescription());
            pst.setLong(4, item.getCreated());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try (PreparedStatement pst = conn.prepareStatement("delete from Items where id = ?;");) {
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void replace(String id, Item item) {
        this.delete(id);
        this.add(item);
    }

    public List<Item> findAll() {
        List<Item> resultList = new ArrayList<>();

        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from Items;")) {
            while (rs.next()) {
                resultList.add(this.createItem(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public Item findById(String id) {
        Item resultItem = null;

        try (PreparedStatement pst = conn.prepareStatement("select * from Items where id = ?;")) {

            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                resultItem = this.createItem(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultItem;
    }

    public List<Item> findByName(String key) {
        List<Item> resultList = new ArrayList<>();

        try (PreparedStatement pst = conn.prepareStatement("select * from Items where name = ?;")) {

            pst.setString(1, key);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                resultList.add(this.createItem(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public void close() throws Exception {
        this.conn.close();
    }

    private Item createItem(ResultSet rs) throws SQLException {
        return new Item(rs.getString("id"), rs.getString("name"),
                rs.getString("description"), rs.getLong("created"));
    }

    private String generateID() {
        Random rand = new Random();
        return String.valueOf(System.currentTimeMillis() + rand.nextInt());
    }
}
