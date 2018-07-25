package ru.job4j.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();
    private final DispatchPattern dispatchPattern = new DispatchPattern(logic);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(logic.findAll().toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String action = req.getParameter("action");
        if (dispatchPattern.makeAction(action, req)) {
            writer.append("request was successful");
            writer.flush();
        } else {
            writer.append("request failed");
            writer.flush();
        }
    }
}
