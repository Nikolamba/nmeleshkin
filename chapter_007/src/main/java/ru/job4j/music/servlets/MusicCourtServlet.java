package ru.job4j.music.servlets;

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

    private final RepositoryUser repositoryUser = RepositoryUser.getInstance();
    private final DaoAdress daoAdress = DaoAdress.getInstance();
    private final DaoRole daoRole = DaoRole.getInstance();
    private final DaoMusicType daoMusicType = DaoMusicType.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", repositoryUser.findAllUsers());
        req.setAttribute("adresses", daoAdress.findAll());
        req.setAttribute("roles", daoRole.findAll());
        req.setAttribute("musicTypes", daoMusicType.findAll());
        req.getRequestDispatcher("/WEB-INF/music_views/index.jsp").forward(req, resp);
    }
}
