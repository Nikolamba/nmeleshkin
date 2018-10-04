package ru.job4j.music.servlets;

import ru.job4j.music.RepositoryUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class SigninServlet extends HttpServlet {

    private final RepositoryUser repositoryUser = RepositoryUser.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/music_views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = Integer.valueOf(req.getParameter("id").trim());
        String nameUser = req.getParameter("name").trim();

        if (repositoryUser.isCredentional(idUser, nameUser)) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("id", idUser);
                session.setAttribute("role", repositoryUser.getUser(idUser).getRole().getRole().trim());
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.getServletContext().setAttribute("error", "Credentional invalid");
            doGet(req, resp);
        }
    }
}
