package ru.job4j.music;

import ru.job4j.music.dao.DaoAdress;
import ru.job4j.music.dao.DaoMusicType;
import ru.job4j.music.dao.DaoRole;
import ru.job4j.music.models.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class LogicLayer {

    private final static LogicLayer INSTANCE = new LogicLayer();
    private final RepositoryUser repositoryUser = RepositoryUser.getInstance();
    private final DaoAdress daoAdress = DaoAdress.getInstance();
    private final DaoRole daoRole = DaoRole.getInstance();
    private final DaoMusicType daoMusicType = DaoMusicType.getInstance();

    private LogicLayer() { }

    public static LogicLayer getInstance() {
        return INSTANCE;
    }

    public FullUser create(HttpServletRequest req) {
        FullUser result = null;
        User user = null;
        Adress adress = null;
        Role role = null;
        if (checkNumberParam("id", req) && checkStringParam("name", req)) {
            if (repositoryUser.getUser(Integer.valueOf(req.getParameter("id"))).getUser() == null) {
                user = new User(Integer.valueOf(req.getParameter("id")),
                        req.getParameter("name"));
            } else {
                req.getServletContext().setAttribute("error", "user id already exists");
                return result;
            }

        }
        if (checkNumberParam("zipcode", req) && checkStringParam("city", req)
                && checkStringParam("street", req) && checkNumberParam("housenum", req)) {
            adress = new Adress(Integer.valueOf(req.getParameter("zipcode")),
                    req.getParameter("city"),
                    req.getParameter("street"),
                    Integer.valueOf(req.getParameter("housenum")));
        }
        if (checkNumberParam("autorization", req)) {
            role = DaoRole.getInstance().findById(Integer.valueOf(req.getParameter("autorization")));
        }

        List<MusicType> musicTypeList = new ArrayList<>();
        Map<String, String[]> map = req.getParameterMap();
        for (String str : map.get("music_types[]")) {
            musicTypeList.add(DaoMusicType.getInstance().findById(Integer.valueOf(str)));
        }
        if (user != null && adress != null && role != null) {
            result = repositoryUser.create(user, adress, role, musicTypeList);
        }
        return result;
    }

    public FullUser getUser(HttpServletRequest req) {
        FullUser result = null;
        if (checkNumberParam("id", req)) {
            result = repositoryUser.getUser(Integer.valueOf(req.getParameter("id").trim()));
        }
        return result;
    }

    public List<FullUser> findAllUsers() {
        return this.repositoryUser.findAllUsers();
    }

    public List<Adress> findAllAdr() {
        return this.daoAdress.findAll();
    }

    public List<Role> findAllRoles() {
        return this.daoRole.findAll();
    }

    public List<MusicType> findAllMusicTypes() {
        return this.daoMusicType.findAll();
    }

    public FullUser findUserByAdress(HttpServletRequest req) {
        int adressId = Integer.valueOf(req.getParameter("adress"));
        return repositoryUser.findUserByAdress(DaoAdress.getInstance().findById(adressId));
    }

    public List<FullUser> findUserByRole(HttpServletRequest req) {
        int roleId = Integer.valueOf(req.getParameter("role"));
        return repositoryUser.findUserByRole(DaoRole.getInstance().findById(roleId));
    }

    public List<FullUser> findUserByMusicType(HttpServletRequest req) {
        int musicTypeId = Integer.valueOf(req.getParameter("musicType"));
        return repositoryUser.findUserByMusicType(DaoMusicType.getInstance().findById(musicTypeId));
    }

    public boolean isCredentional(int idUser, String nameUser) {
        return this.repositoryUser.isCredentional(idUser, nameUser);
    }

    public boolean checkNumberParam(String param, HttpServletRequest req) {
        if (req.getParameter(param) == null) {
            req.getServletContext().setAttribute("error", String.format("%s isn't set", param));
            return false;
        }
        try {
            Integer.valueOf(req.getParameter(param).trim());
        } catch (NumberFormatException e) {
            req.getServletContext().setAttribute("error", String.format("%s isn't a number", param));
            return false;
        }
        return true;
    }

    public boolean checkStringParam(String param, HttpServletRequest req) {
        if (req.getParameter(param) == null) {
            req.getServletContext().setAttribute("error", String.format("%s isn't set", param));
            return false;
        }
        return true;
    }
}
