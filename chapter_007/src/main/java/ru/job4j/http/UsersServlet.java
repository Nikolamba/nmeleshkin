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
public class UsersServlet extends HttpServlet {

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
                + this.fillTable(req)
                + "</body>\n"
                + "</html>");
        writer.flush();
    }

    private String fillTable(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder("<table>");
        for (User user : logic.findAll()) {
            sb.append("<tr>"
                    + "<td>" + user.getId() + "</td>"
                    + "<td>" + user.getName() + "</td>"
                    + "<td>" + user.getLogin() + "</td>"
                    + "<td>" + user.getEmail() + "</td>"
                    + "<td>" + user.getCreateDate() + "</td>"
                    + "<td>"
                    + "<form action = '" + req.getContextPath() + "/edit' method = 'get'>"
                    + "<input type = 'hidden' name = 'id' value = '" + user.getId() + "'/>"
                    + "<input type = 'submit' value = 'Edit'/>"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action = '" + req.getContextPath() + "/list' method = 'post'>"
                    + "<input type = 'hidden' name = 'id' value = '" + user.getId() + "'/>"
                    + "<input type = 'submit' value = 'Delete'/>"
                    + "</td>"
                    + "</form>"
                    + "</td>"
                    + "</tr>");
        }
        sb.append("<tr><td>"
                + "<form action = '" + req.getContextPath() + "/create' method = 'get'>"
                + "<input type = 'submit' value = 'Create new User'/>"
                + "</td></tr>");
        sb.append("</table");
        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logic.delete(req);
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
