package carsales.servlets;

import carsales.LogicLayer;
import carsales.dao.DAOCarImp;
import carsales.models.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarSalesServlet extends HttpServlet {

    private final LogicLayer logic = LogicLayer.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> cars;
        Map<String, Integer> filterMap = new HashMap<>();

        if (req.getParameter("brandId") != null && Integer.valueOf(req.getParameter("brandId")) != -1) {
            filterMap.put("brandFilter", Integer.valueOf(req.getParameter("brandId")));
        }
        if (req.getParameter("onlyFoto") != null && Integer.valueOf(req.getParameter("onlyFoto")) != 0) {
            filterMap.put("onlyFotoFilter", 1);
        }
        if (req.getParameter("currentData") != null && Integer.valueOf(req.getParameter("currentData")) != 0) {
            filterMap.put("currentDataFilter", 1);
        }
        cars = (filterMap.isEmpty()) ? logic.findAllCars() : logic.useFilters(filterMap);
        if (cars != null) {
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter printWriter = resp.getWriter();
            printWriter.append(mapper.writeValueAsString(cars));
            printWriter.flush();
        }
    }
}
