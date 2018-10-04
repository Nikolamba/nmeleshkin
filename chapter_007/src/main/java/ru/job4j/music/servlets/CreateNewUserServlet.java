package ru.job4j.music.servlets;

import ru.job4j.music.dao.DaoMusicType;
import ru.job4j.music.models.Adress;
import ru.job4j.music.models.MusicType;
import ru.job4j.music.models.Role;
import ru.job4j.music.dao.DaoRole;
import ru.job4j.music.RepositoryUser;
import ru.job4j.music.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class CreateNewUserServlet extends HttpServlet {

    private final RepositoryUser repositoryUser = RepositoryUser.getInstance();
    private final DaoRole daoRole = DaoRole.getInstance();
    private final DaoMusicType daoMusicType = DaoMusicType.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", daoRole.findAll());
        req.setAttribute("music_types", daoMusicType.findAll());
        req.getRequestDispatcher("/WEB-INF/music_views/newuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(Integer.valueOf(req.getParameter("id")),
                req.getParameter("name"));
        Adress adress = new Adress(Integer.valueOf(req.getParameter("zipcode")),
                req.getParameter("city"),
                req.getParameter("street"),
                Integer.valueOf(req.getParameter("housenum")));
        Role role = DaoRole.getInstance().findById(Integer.valueOf(req.getParameter("autorization")));
        List<MusicType> musicTypeList = new ArrayList<>();
        Map<String, String[]> map = req.getParameterMap();
        for (String str : map.get("music_types[]")) {
            musicTypeList.add(DaoMusicType.getInstance().findById(Integer.valueOf(str)));
        }
        repositoryUser.create(user, adress, role, musicTypeList);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
