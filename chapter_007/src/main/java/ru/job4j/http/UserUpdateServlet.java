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
public class UserUpdateServlet extends HttpServlet {

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
        logic.update(req);
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }

    private String fillResponse(HttpServletRequest req) {
        User user = logic.findById(Integer.valueOf(req.getParameter("id")));
        StringBuilder string = new StringBuilder();
        string.append(String.format("<form action = '%s/edit' method = 'post'>", req.getContextPath()));
        string.append(String.format("ID : <input type = 'text' name = 'id' value = '%s'/>", user.getId()));
        string.append(String.format("Name : <input type = 'text' name = 'name' value = '%s'/>", user.getName()));
        string.append(String.format("Login : <input type = 'text' name = 'login' value = '%s'/>", user.getLogin()));
        string.append(String.format("EMail : <input type = 'text' name = 'email' value = '%s'/>", user.getEmail()));
        string.append("<input type = 'submit' value = 'Edit user'/>");
        string.append("</form>");
        return string.toString();
    }
}
