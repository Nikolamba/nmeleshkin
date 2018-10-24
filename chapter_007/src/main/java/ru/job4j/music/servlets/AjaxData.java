package ru.job4j.music.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.music.LogicLayer;
import ru.job4j.music.dao.DaoAdress;
import ru.job4j.music.dao.DaoMusicType;
import ru.job4j.music.dao.DaoRole;
import ru.job4j.music.RepositoryUser;
import ru.job4j.music.models.FullUser;

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
public class AjaxData extends HttpServlet {

    private final LogicLayer logicLayer = LogicLayer.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        if (logicLayer.checkNumberParam("adress", req)) {
            FullUser user = logicLayer.findUserByAdress(req);
            writer.append(mapper.writeValueAsString(user));
        } else if (logicLayer.checkNumberParam("role", req)) {
            List<FullUser> users = logicLayer.findUserByRole(req);
            writer.append(mapper.writeValueAsString(users));
        } else if (logicLayer.checkNumberParam("musicType", req)) {
            List<FullUser> users = logicLayer.findUserByMusicType(req);
            writer.append(mapper.writeValueAsString(users));
        }
        writer.flush();
    }
}
