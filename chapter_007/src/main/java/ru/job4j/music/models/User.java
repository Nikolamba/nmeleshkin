package ru.job4j.music.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class User {
    private int id;
    private String name;
    private Integer idAdress;
    private Integer idRole;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.idRole = 3;
        this.idAdress = null;
    }

    public User(int id, String name, Integer idAdress, Integer idRole) {
        this.id = id;
        this.name = name;
        this.idAdress = idAdress;
        this.idRole = idRole;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getIdAdress() {
        return idAdress;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdAdress(Integer idAdress) {
        this.idAdress = idAdress;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
}
