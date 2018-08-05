package ru.job4j.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserCreateServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logic.add(req);
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
