package ru.job4j.music;

import ru.job4j.music.dao.*;
import ru.job4j.music.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class RepositoryUser {

    private final static RepositoryUser INSTANCE = new RepositoryUser();

    private RepositoryUser() { }

    public static RepositoryUser getInstance() {
        return INSTANCE;
    }

    public FullUser create(User user, Adress adress, Role role, List<MusicType> musicTypeList) {

        DaoAdress.getInstance().create(adress);
        user.setIdAdress(adress.getId());
        user.setIdRole(role.getId());
        DaoUser.getInstance().create(user);
        for (MusicType mt : musicTypeList) {
            DaoUsersMusicType.getInstance().create(new UsersMusicType(user.getId(), mt.getId()));
        }
        return new FullUser(user, adress, role, musicTypeList);
    }

    public List<FullUser> findAllUsers() {
        List<User> list = DaoUser.getInstance().findAll();
        List<FullUser> result = new ArrayList<>();
        for (User user : list) {
            result.add(this.getUser(user.getId()));
        }
        return result;
    }

    public FullUser getUser(int idUser) {
        User user = DaoUser.getInstance().findById(idUser);
        List<MusicType> musicTypeList = new ArrayList<>();
        List<UsersMusicType> list = DaoUsersMusicType.getInstance().findAllForUser(user);
        for (UsersMusicType umt : list) {
            musicTypeList.add(DaoMusicType.getInstance().findById(umt.getMusicTypeId()));
        }
        FullUser result = new FullUser(user,
                DaoAdress.getInstance().findById(user.getIdAdress()),
                DaoRole.getInstance().findById(user.getIdRole()),
                musicTypeList);
        return result;
    }

    public FullUser findUserByAdress(Adress adress) {
        List<User> users = DaoUser.getInstance().findAll();
        FullUser fullUser = null;
        for (User user : users) {
            if (user.getIdAdress() == adress.getId()) {
                fullUser = this.getUser(user.getId());
                break;
            }
        }
        return fullUser;
    }

    public List<FullUser> findUserByRole(Role role) {
        List<User> users = DaoUser.getInstance().findAll();
        List<FullUser> result = new ArrayList<>();
        for (User user : users) {
            if (user.getIdRole() == role.getId()) {
                result.add(this.getUser(user.getId()));
            }
        }
        return result;
    }

    public List<FullUser> findUserByMusicType(MusicType musicType) {
        List<FullUser> result = new ArrayList<>();
        List<UsersMusicType> list = DaoUsersMusicType.getInstance().findAllForMusicType(musicType);
        for (UsersMusicType umt : list) {
            result.add(this.getUser(umt.getUserId()));
        }
        return result;
    }

    public boolean isCredentional(int idUser, String nameUser) {
        boolean exist = false;
        List<FullUser> list = this.findAllUsers();
        for (FullUser fullUser : list) {
            if (fullUser.getUser().getId() == idUser && fullUser.getUser().getName().trim().equals(nameUser)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

}
