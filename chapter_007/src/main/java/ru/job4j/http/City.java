package ru.job4j.http;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class City extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, List<String>> cities = new HashMap<>();
        cities.put("russia", new ArrayList<>(Arrays.asList("Moscow", "Spb", "Kazan", "Saratov")));
        cities.put("belarus", new ArrayList<>(Arrays.asList("Minsk", "Mogilev", "Gomel", "Vitebsk")));
        cities.put("ukraine", new ArrayList<>(Arrays.asList("Kiev", "Kharkov", "Lvov", "Chernigov")));
        String country = req.getParameter("country");
        PrintWriter writer = resp.getWriter();

        for (String str : cities.get(country)) {
            writer.append("<option name = \"");
            writer.append(str.toLowerCase());
            writer.append("\">");
            writer.append(str);
            writer.append("</option>");
        }
        System.out.println(writer.toString());
        writer.flush();
    }
}
