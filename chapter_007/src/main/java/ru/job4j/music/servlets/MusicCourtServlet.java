package ru.job4j.music.servlets;

import ru.job4j.music.LogicLayer;
import ru.job4j.music.dao.DaoAdress;
import ru.job4j.music.dao.DaoMusicType;
import ru.job4j.music.dao.DaoRole;
import ru.job4j.music.RepositoryUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class MusicCourtServlet extends HttpServlet {

    private final LogicLayer logicLayer = LogicLayer.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", logicLayer.findAllUsers());
        req.setAttribute("adresses", logicLayer.findAllAdr());
        req.setAttribute("roles", logicLayer.findAllRoles());
        req.setAttribute("musicTypes", logicLayer.findAllMusicTypes());
        req.getRequestDispatcher("/WEB-INF/music_views/index.jsp").forward(req, resp);
    }
}
