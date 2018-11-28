package carsales.servlets;

import carsales.LogicLayer;
import carsales.dao.DAOCarImp;
import carsales.models.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CarSalesServlet extends HttpServlet {

    private final LogicLayer logic = LogicLayer.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> cars = logic.findAllCars();
        if (req.getParameter("brandId") != null) {
            cars = logic.findCarsByBrand(req);
        } else if (req.getParameter("onlyFoto") != null) {
            cars = logic.findCarsOnlyFoto();
        } else if (req.getParameter("currentData") != null) {
            cars = logic.findCarsCurrentData();
        }
        if (cars != null) {
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter printWriter = resp.getWriter();
            printWriter.append(mapper.writeValueAsString(cars));
            printWriter.flush();
        }
    }
}
