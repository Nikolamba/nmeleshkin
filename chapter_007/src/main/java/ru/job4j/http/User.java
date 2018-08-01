package ru.job4j.http;

import java.time.LocalDate;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private LocalDate createDate;

    User(int id, String name) {
        this.id = id;
        this.name = name;
        createDate = LocalDate.now();
    }

    User(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        createDate = LocalDate.now();
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getLogin() {
        return login;
    }

    String getEmail() {
        return email;
    }

    LocalDate getCreateDate() {
        return createDate;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", this.getId(), this.getName(),
                this.getLogin(), this.getEmail(), this.getCreateDate());

    }
}
