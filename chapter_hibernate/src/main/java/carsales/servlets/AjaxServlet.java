package carsales.servlets;

import carsales.LogicLayer;
import carsales.dao.DAOBodyTypeImp;
import carsales.dao.DAOBrandImp;
import carsales.dao.DAOModelImp;
import carsales.dao.DAOUserImp;
import carsales.models.BodyType;
import carsales.models.Brand;
import carsales.models.Model;
import carsales.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AjaxServlet extends HttpServlet {

    private final LogicLayer logic = LogicLayer.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = resp.getWriter();
        if (req.getParameter("brand") != null) {
            Brand brand = new Brand();
            brand.setId(Integer.valueOf(req.getParameter("brand")));
            List<Model> models = logic.findModelsByBrand(brand);
            printWriter.append(mapper.writeValueAsString(models));
        }
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        if (req.getParameter("name") != null && req.getParameter("pass") != null) {
            String name = req.getParameter("name").trim();
            String pass = req.getParameter("pass").trim();
            if (logic.findAllUsers().stream().anyMatch(user -> user.getName().trim().equals(name))) {
                printWriter.append("user with this name is already registered");
            } else {
                logic.addUser(new User(name, pass));
                printWriter.append("registration successfully completed");
            }
        } else {
            printWriter.append("field fill error");
        }
        printWriter.flush();
    }
}
