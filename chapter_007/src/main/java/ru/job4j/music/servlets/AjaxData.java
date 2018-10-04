package ru.job4j.music.servlets;

import org.codehaus.jackson.map.ObjectMapper;
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

    private final RepositoryUser repositoryUser = RepositoryUser.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("adress") != null) {
            int adressId = Integer.valueOf(req.getParameter("adress"));
            FullUser user = repositoryUser.findUserByAdress(DaoAdress.getInstance().findById(adressId));
            writer.append(mapper.writeValueAsString(user));
        } else if (req.getParameter("role") != null) {
            int roleId = Integer.valueOf(req.getParameter("role"));
            List<FullUser> users = repositoryUser.findUserByRole(DaoRole.getInstance().findById(roleId));
            writer.append(mapper.writeValueAsString(users));
        } else if (req.getParameter("musicType") != null) {
            int musicTypeId = Integer.valueOf(req.getParameter("musicType"));
            List<FullUser> users = repositoryUser.findUserByMusicType(DaoMusicType.getInstance().findById(musicTypeId));
            writer.append(mapper.writeValueAsString(users));
        }
        writer.flush();
    }
}
