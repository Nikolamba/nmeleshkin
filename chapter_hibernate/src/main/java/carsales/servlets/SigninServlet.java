package carsales.servlets;

import carsales.LogicLayer;
import carsales.dao.DAOUserImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninServlet extends HttpServlet {

    private final LogicLayer logic = LogicLayer.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/car_sales/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (logic.setSessionUser(req, resp)) {
            resp.sendRedirect(String.format("%s/car_sales_views/cars.html", req.getContextPath()));
        } else {
            req.getRequestDispatcher("/WEB-INF/car_sales/signin.jsp").forward(req, resp);
        }
    }
}
