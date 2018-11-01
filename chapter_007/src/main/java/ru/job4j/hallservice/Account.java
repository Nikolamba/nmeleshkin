package ru.job4j.hallservice;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Account {
    private int id;
    private String userName;
    private String phoneNumber;

    public Account(String userName, String phoneNumber) {
        this.id = -1;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
