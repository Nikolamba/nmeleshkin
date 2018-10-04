package ru.job4j.music.models;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Role {

    private int id;
    private String role;

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
