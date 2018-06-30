package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StoreSQL implements AutoCloseable {

    private Connection conn;

    StoreSQL(Properties config) {
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(config.getProperty("url"));
            System.out.println("База данных создана или уже существовала");
            DatabaseMetaData dm = conn.getMetaData();
            rs = dm.getTables(null, null, "entry", null);
            if (!rs.next()) {
                st = conn.createStatement();
                st.execute("CREATE TABLE entry ("
                            + "field integer"
                            + ");");
                System.out.println("Табилца entry создана");
            }
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void generate(final int n) {

        try (PreparedStatement ps = conn.prepareStatement("Insert into entry VALUES(?)");
             Statement st = conn.createStatement()) {

            st.execute("Delete from entry");
            for (int i = 1; i <= n; i++) {
                ps.setString(1, String.valueOf(i));
                ps.executeUpdate();
                System.out.println("Создана запись - " + i);
            }
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }
    }

    public List<StoreXML.Entry> getEntries() {
        List<StoreXML.Entry> list = new ArrayList<>();

        try (Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from entry;")) {
            while (rs.next()) {
                list.add(new StoreXML.Entry(rs.getInt("field")));
            }
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }
}
