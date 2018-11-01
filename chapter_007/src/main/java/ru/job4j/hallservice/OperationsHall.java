package ru.job4j.hallservice;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class OperationsHall implements Operations, Closeable {

    private final static OperationsHall INSTANCE = new OperationsHall();
    private Connection connection;

    private OperationsHall() {
        BasicDataSource source = DataBaseConnection.getInstance().getSource();
        try {
            connection = source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static OperationsHall getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addAccount(int row, int place, Account account) {
        int result = 0;
        try {
            connection.setAutoCommit(false);
            this.insertAccount(account);
            result = this.updateHalls(account, row, place);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlExc) {
                sqlExc.printStackTrace();
            }
        }
        return result != 0;
    }

    @Override
    public boolean deleteAccount(int row, int place) {
        boolean result = false;
        try (PreparedStatement st = connection.prepareStatement(
                    "UPDATE halls set account_id = null where row = ? AND place = ?")) {
            st.setInt(1, row);
            st.setInt(2, place);
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean editAccount(int row, int place, Account newAccount) {
        int result = 0;
        try {
            connection.setAutoCommit(false);
            this.deleteAccount(row, place);
            this.insertAccount(newAccount);
            result = this.updateHalls(newAccount, row, place);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlExc) {
                sqlExc.printStackTrace();
            }
        }
        return result != 0;
    }

    @Override
    public boolean isTaken(int row, int place) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement("Select * from halls where row = ? AND place = ?;")) {
            ps.setInt(1, row);
            ps.setInt(2, place);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("account_id") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Place> getAllPlaces() {
        List<Place> places = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM halls;");
            while (rs.next()) {
                int row = rs.getInt("row");
                int place = rs.getInt("place");
                Integer accountId = rs.getInt("account_id");
                places.add(new Place(row, place, accountId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }

    @Override
    public Account findAccountById(int accountId) {
        Account result = null;
        try (PreparedStatement st = connection.prepareStatement("Select * from account where id = ?;")) {
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = new Account(rs.getString("name"),
                        rs.getString("phone_number"));
                result.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertAccount(Account account) {
        try (PreparedStatement st = connection.prepareStatement("INSERT into account VALUES (DEFAULT, ?, ?) RETURNING id;")) {
            st.setString(1, account.getUserName());
            st.setString(2, account.getPhoneNumber());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                account.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int updateHalls(Account account, int row, int place) {
        int result = 0;
        try (PreparedStatement st = connection.prepareStatement("UPDATE halls set account_id = ? where row = ? and place = ?;")) {
            st.setInt(1, account.getId());
            st.setInt(2, row);
            st.setInt(3, place);
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
