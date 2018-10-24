package ru.job4j.music.servlets;

import ru.job4j.music.LogicLayer;
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

    private final LogicLayer logicLayer = LogicLayer.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", logicLayer.findAllRoles());
        req.setAttribute("music_types", logicLayer.findAllMusicTypes());
        req.getRequestDispatcher("/WEB-INF/music_views/newuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logicLayer.create(req);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
