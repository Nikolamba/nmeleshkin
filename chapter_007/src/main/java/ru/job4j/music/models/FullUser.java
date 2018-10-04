package ru.job4j.music.models;

import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class FullUser {
    private User user;
    private Adress adress;
    private Role role;
    private List<MusicType> musicTypeList;

    public FullUser(User user, Adress adress, Role role, List<MusicType> musicTypeList) {
        this.user = user;
        this.adress = adress;
        this.role = role;
        this.musicTypeList = musicTypeList;
    }

    public User getUser() {
        return user;
    }

    public Adress getAdress() {
        return adress;
    }

    public Role getRole() {
        return role;
    }

    public List<MusicType> getMusicTypeList() {
        return musicTypeList;
    }
}
