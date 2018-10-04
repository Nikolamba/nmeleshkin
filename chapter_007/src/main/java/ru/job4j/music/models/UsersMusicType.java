package ru.job4j.music.models;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class UsersMusicType {

    private int id;
    private int userId;
    private int musicTypeId;

    public UsersMusicType(int id, int userId, int musicTypeId) {
        this.id = id;
        this.userId = userId;
        this.musicTypeId = musicTypeId;
    }

    public UsersMusicType(int userId, int musicTypeId) {
        this.userId = userId;
        this.musicTypeId = musicTypeId;
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getMusicTypeId() {
        return musicTypeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMusicTypeId(int musicTypeId) {
        this.musicTypeId = musicTypeId;
    }
}
