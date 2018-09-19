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
    private String password;
    private String email;
    private LocalDate createDate;
    private Role role;
    private String country;
    private String city;

    User(int id, String name) {
        this.id = id;
        this.name = name;
        createDate = LocalDate.now();
        this.login = name;
        this.password = "pass";
        this.role = new Role("administrator");
    }

    User(int id, String name, String login, String password, String email, Role role, String country, String city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        createDate = LocalDate.now();
        this.role = role;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", this.getId(), this.getName(),
                this.getLogin(), this.getPassword(), this.getEmail(), this.getCreateDate());

    }
}
