package ru.job4j.hallservice;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class HallServlet extends HttpServlet {

    private final Logic connection = Logic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Place> places = connection.getAllPlaces();
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        writer.append(mapper.writeValueAsString(places));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (connection.addAccount(req)) {
            resp.sendRedirect(String.format("%s/index.html", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/error.html", req.getContextPath()));
        }
    }
}
