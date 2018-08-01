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
public class UserCreateServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title></title>\n"
                + "</head>\n"
                + "<body>\n"
                + this.fillResponse(req)
                + "</body>\n"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logic.add(req);
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }

    private String fillResponse(HttpServletRequest req) {
        StringBuilder string = new StringBuilder();
        string.append(String.format("<form action = '%s/create' method = 'post'>", req.getContextPath()));
        string.append("ID : <input type = 'text' name = 'id'/>");
        string.append("Name : <input type = 'text' name = 'name'/>");
        string.append("Login : <input type = 'text' name = 'login'/>");
        string.append("EMail : <input type = 'text' name = 'email'/>");
        string.append("Create Date : <input type = 'text' name = 'created'/>");
        string.append("<input type = 'submit' value = 'Create new user'/>");
        string.append("</form>");
        return string.toString();
    }
}
